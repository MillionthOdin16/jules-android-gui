package com.jules.android.gui.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.jules.android.gui.R
import com.jules.android.gui.api.JulesApiClient
import com.jules.android.gui.data.repository.JulesRepository
import com.jules.android.gui.databinding.FragmentCommandsBinding
import com.jules.android.gui.utils.PreferencesHelper
import kotlinx.coroutines.launch

/**
 * Commands Fragment - Interface for creating Jules sessions
 * Allows users to input prompts and create sessions with Jules AI
 */
class CommandsFragment : Fragment() {
    
    private var _binding: FragmentCommandsBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var repository: JulesRepository
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
        
        repository = JulesRepository()
        preferencesHelper = PreferencesHelper(requireContext())
        
        // Initialize API client with saved key
        val apiKey = preferencesHelper.getApiKey()
        if (apiKey.isNotBlank()) {
            JulesApiClient.setApiKey(apiKey)
        }
        
        setupListeners()
        checkConfiguration()
    }
    
    private fun checkConfiguration() {
        if (!preferencesHelper.hasApiKey()) {
            binding.outputText.text = getString(R.string.error_api_key_not_set)
            binding.executeButton.isEnabled = false
            return
        }
        
        if (preferencesHelper.getSelectedSource() == null) {
            binding.outputText.text = getString(R.string.error_no_source_selected)
            binding.executeButton.isEnabled = false
            return
        }
        
        binding.executeButton.isEnabled = true
    }
    
    private fun setupListeners() {
        binding.executeButton.setOnClickListener {
            createSession()
        }
        
        binding.clearButton.setOnClickListener {
            clearOutput()
        }
        
        binding.commandInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                createSession()
                true
            } else {
                false
            }
        }
    }
    
    private fun createSession() {
        val prompt = binding.commandInput.text.toString().trim()
        
        if (prompt.isEmpty()) {
            Snackbar.make(
                binding.root,
                R.string.error_command_empty,
                Snackbar.LENGTH_SHORT
            ).show()
            return
        }
        
        if (!preferencesHelper.hasApiKey()) {
            Snackbar.make(
                binding.root,
                R.string.error_api_key_not_set,
                Snackbar.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.navigation_settings)
            return
        }
        
        val sourceName = preferencesHelper.getSelectedSource()
        if (sourceName == null) {
            Snackbar.make(
                binding.root,
                R.string.error_no_source_selected,
                Snackbar.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.navigation_settings)
            return
        }
        
        // Show progress
        binding.progressIndicator.visibility = View.VISIBLE
        binding.executeButton.isEnabled = false
        
        // Create session in background
        lifecycleScope.launch {
            when (val result = repository.createSession(prompt, sourceName)) {
                is JulesRepository.Result.Success -> {
                    val session = result.data
                    displaySessionCreated(prompt, session.name, session.state ?: "PENDING")
                    
                    Snackbar.make(
                        binding.root,
                        R.string.success_command_executed,
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                is JulesRepository.Result.Error -> {
                    displayError(prompt, result.message)
                    
                    val errorMessage = when {
                        result.code == 401 || result.code == 403 -> 
                            getString(R.string.error_authentication)
                        result.message.contains("network", ignoreCase = true) -> 
                            getString(R.string.error_network)
                        else -> result.message
                    }
                    
                    Snackbar.make(
                        binding.root,
                        errorMessage,
                        Snackbar.LENGTH_LONG
                    ).show()
                }
                else -> {}
            }
            
            // Hide progress
            binding.progressIndicator.visibility = View.GONE
            binding.executeButton.isEnabled = true
        }
    }
    
    private fun displaySessionCreated(prompt: String, sessionName: String, state: String) {
        val currentOutput = binding.outputText.text.toString()
        val newOutput = if (currentOutput == getString(R.string.no_commands_yet) || 
                            currentOutput == getString(R.string.error_api_key_not_set) ||
                            currentOutput == getString(R.string.error_no_source_selected)) {
            buildSessionOutput(prompt, sessionName, state)
        } else {
            "$currentOutput\n\n${buildSessionOutput(prompt, sessionName, state)}"
        }
        
        binding.outputText.text = newOutput
        
        // Auto-scroll to bottom if enabled
        if (preferencesHelper.isAutoScrollEnabled()) {
            binding.outputScrollView.post {
                binding.outputScrollView.fullScroll(View.FOCUS_DOWN)
            }
        }
    }
    
    private fun displayError(prompt: String, error: String) {
        val errorOutput = buildErrorOutput(prompt, error)
        val currentOutput = binding.outputText.text.toString()
        
        val newOutput = if (currentOutput == getString(R.string.no_commands_yet) ||
                            currentOutput == getString(R.string.error_api_key_not_set) ||
                            currentOutput == getString(R.string.error_no_source_selected)) {
            errorOutput
        } else {
            "$currentOutput\n\n$errorOutput"
        }
        
        binding.outputText.text = newOutput
        
        if (preferencesHelper.isAutoScrollEnabled()) {
            binding.outputScrollView.post {
                binding.outputScrollView.fullScroll(View.FOCUS_DOWN)
            }
        }
    }
    
    private fun buildSessionOutput(prompt: String, sessionName: String, state: String): String {
        return """
            |📝 Prompt: $prompt
            |✅ Session Created: $sessionName
            |📊 State: $state
            |
            |Visit jules.google to view session details and activities.
        """.trimMargin()
    }
    
    private fun buildErrorOutput(prompt: String, error: String): String {
        return """
            |📝 Prompt: $prompt
            |❌ Error: $error
        """.trimMargin()
    }
    
    private fun clearOutput() {
        binding.outputText.text = getString(R.string.no_commands_yet)
        binding.commandInput.text?.clear()
        checkConfiguration()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
