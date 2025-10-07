package com.jules.android.gui.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.jules.android.gui.R
import com.jules.android.gui.databinding.FragmentSettingsBinding
import com.jules.android.gui.utils.PreferencesHelper

/**
 * Settings Fragment - Configuration options for the application
 * Allows users to customize CLI path, auto-scroll, and other preferences
 */
class SettingsFragment : Fragment() {
    
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var preferencesHelper: PreferencesHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        preferencesHelper = PreferencesHelper(requireContext())
        
        loadSettings()
        setupListeners()
    }
    
    private fun loadSettings() {
        binding.cliPathInput.setText(preferencesHelper.getCliPath())
        binding.autoScrollSwitch.isChecked = preferencesHelper.isAutoScrollEnabled()
        binding.saveHistorySwitch.isChecked = preferencesHelper.isSaveHistoryEnabled()
    }
    
    private fun setupListeners() {
        binding.saveSettingsButton.setOnClickListener {
            saveSettings()
        }
    }
    
    private fun saveSettings() {
        val cliPath = binding.cliPathInput.text.toString().trim()
        val autoScroll = binding.autoScrollSwitch.isChecked
        val saveHistory = binding.saveHistorySwitch.isChecked
        
        preferencesHelper.setCliPath(cliPath)
        preferencesHelper.setAutoScrollEnabled(autoScroll)
        preferencesHelper.setSaveHistoryEnabled(saveHistory)
        
        Snackbar.make(
            binding.root,
            "Settings saved successfully",
            Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
