package com.jules.android.gui.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.jules.android.gui.R
import com.jules.android.gui.api.JulesApiClient
import com.jules.android.gui.data.models.Source
import com.jules.android.gui.data.repository.JulesRepository
import com.jules.android.gui.databinding.FragmentSettingsBinding
import com.jules.android.gui.utils.PreferencesHelper
import kotlinx.coroutines.launch

/**
 * Settings Fragment - Configuration options for the application
 * Allows users to set API key, select source, and customize preferences
 */
class SettingsFragment : Fragment() {
    
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var preferencesHelper: PreferencesHelper
    private lateinit var repository: JulesRepository
    
    private var sources: List<Source> = emptyList()

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
        repository = JulesRepository()
        
        loadSettings()
        setupListeners()
    }
    
    private fun loadSettings() {
        // Load API key
        val apiKey = preferencesHelper.getApiKey()
        binding.apiKeyInput.setText(apiKey)
        
        // Load other settings
        binding.autoScrollSwitch.isChecked = preferencesHelper.isAutoScrollEnabled()
        binding.saveHistorySwitch.isChecked = preferencesHelper.isSaveHistoryEnabled()
        
        // Load sources if API key is set
        if (apiKey.isNotBlank()) {
            JulesApiClient.setApiKey(apiKey)
            loadSources()
        }
    }
    
    private fun setupListeners() {
        binding.loadSourcesButton.setOnClickListener {
            val apiKey = binding.apiKeyInput.text.toString().trim()
            if (apiKey.isBlank()) {
                Snackbar.make(
                    binding.root,
                    R.string.error_api_key_not_set,
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            
            // Save API key and load sources
            JulesApiClient.setApiKey(apiKey)
            preferencesHelper.setApiKey(apiKey)
            loadSources()
        }
        
        binding.saveSettingsButton.setOnClickListener {
            saveSettings()
        }
    }
    
    private fun loadSources() {
        binding.loadSourcesButton.isEnabled = false
        binding.loadSourcesButton.text = getString(R.string.sources_loading)
        
        lifecycleScope.launch {
            when (val result = repository.getSources()) {
                is JulesRepository.Result.Success -> {
                    sources = result.data
                    setupSourceSpinner(sources)
                    Snackbar.make(
                        binding.root,
                        "Loaded ${sources.size} sources",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                is JulesRepository.Result.Error -> {
                    Snackbar.make(
                        binding.root,
                        result.message,
                        Snackbar.LENGTH_LONG
                    ).show()
                }
                else -> {}
            }
            
            binding.loadSourcesButton.isEnabled = true
            binding.loadSourcesButton.text = "Load Sources"
        }
    }
    
    private fun setupSourceSpinner(sources: List<Source>) {
        val sourceNames = sources.map { it.displayName ?: it.name }
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            sourceNames
        )
        binding.sourceSpinner.setAdapter(adapter)
        
        // Set currently selected source
        val selectedSource = preferencesHelper.getSelectedSource()
        selectedSource?.let { sourceName ->
            val index = sources.indexOfFirst { it.name == sourceName }
            if (index >= 0) {
                binding.sourceSpinner.setText(sourceNames[index], false)
            }
        }
        
        // Handle source selection
        binding.sourceSpinner.setOnItemClickListener { _, _, position, _ ->
            val selectedSourceName = sources[position].name
            preferencesHelper.setSelectedSource(selectedSourceName)
            Snackbar.make(
                binding.root,
                getString(R.string.source_selected, sources[position].displayName ?: selectedSourceName),
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
    
    private fun saveSettings() {
        val apiKey = binding.apiKeyInput.text.toString().trim()
        val autoScroll = binding.autoScrollSwitch.isChecked
        val saveHistory = binding.saveHistorySwitch.isChecked
        
        if (apiKey.isBlank()) {
            Snackbar.make(
                binding.root,
                R.string.error_api_key_not_set,
                Snackbar.LENGTH_SHORT
            ).show()
            return
        }
        
        preferencesHelper.setApiKey(apiKey)
        preferencesHelper.setAutoScrollEnabled(autoScroll)
        preferencesHelper.setSaveHistoryEnabled(saveHistory)
        
        JulesApiClient.setApiKey(apiKey)
        
        Snackbar.make(
            binding.root,
            R.string.success_api_key_saved,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
