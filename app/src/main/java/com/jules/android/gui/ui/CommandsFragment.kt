package com.jules.android.gui.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.jules.android.gui.R
import com.jules.android.gui.databinding.FragmentCommandsBinding
import com.jules.android.gui.utils.CommandExecutor
import com.jules.android.gui.utils.PreferencesHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Commands Fragment - Interface for executing Jules CLI commands
 * Allows users to input commands and view their output
 */
class CommandsFragment : Fragment() {
    
    private var _binding: FragmentCommandsBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var commandExecutor: CommandExecutor
    private lateinit var preferencesHelper: PreferencesHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommandsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        commandExecutor = CommandExecutor(requireContext())
        preferencesHelper = PreferencesHelper(requireContext())
        
        setupListeners()
    }
    
    private fun setupListeners() {
        binding.executeButton.setOnClickListener {
            executeCommand()
        }
        
        binding.clearButton.setOnClickListener {
            clearOutput()
        }
        
        binding.commandInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                executeCommand()
                true
            } else {
                false
            }
        }
    }
    
    private fun executeCommand() {
        val command = binding.commandInput.text.toString().trim()
        
        if (command.isEmpty()) {
            Snackbar.make(
                binding.root,
                R.string.error_command_empty,
                Snackbar.LENGTH_SHORT
            ).show()
            return
        }
        
        // Show progress
        binding.progressIndicator.visibility = View.VISIBLE
        binding.executeButton.isEnabled = false
        
        // Execute command in background
        lifecycleScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    commandExecutor.execute(command)
                }
                
                // Update UI with result
                displayOutput(command, result)
                
                Snackbar.make(
                    binding.root,
                    R.string.success_command_executed,
                    Snackbar.LENGTH_SHORT
                ).show()
                
            } catch (e: Exception) {
                displayError(command, e.message ?: getString(R.string.error_execution_failed))
                
                Snackbar.make(
                    binding.root,
                    R.string.error_execution_failed,
                    Snackbar.LENGTH_LONG
                ).show()
            } finally {
                // Hide progress
                binding.progressIndicator.visibility = View.GONE
                binding.executeButton.isEnabled = true
            }
        }
    }
    
    private fun displayOutput(command: String, output: String) {
        val currentOutput = binding.outputText.text.toString()
        val newOutput = if (currentOutput == getString(R.string.no_commands_yet)) {
            buildOutputText(command, output)
        } else {
            "$currentOutput\n\n${buildOutputText(command, output)}"
        }
        
        binding.outputText.text = newOutput
        
        // Auto-scroll to bottom if enabled
        if (preferencesHelper.isAutoScrollEnabled()) {
            binding.outputScrollView.post {
                binding.outputScrollView.fullScroll(View.FOCUS_DOWN)
            }
        }
    }
    
    private fun displayError(command: String, error: String) {
        val errorOutput = "$ $command\n${getString(R.string.command_error)}: $error"
        displayOutput(command, errorOutput)
    }
    
    private fun buildOutputText(command: String, output: String): String {
        return "$ $command\n$output"
    }
    
    private fun clearOutput() {
        binding.outputText.text = getString(R.string.no_commands_yet)
        binding.commandInput.text?.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
