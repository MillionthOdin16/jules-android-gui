package com.jules.android.gui.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jules.android.gui.R
import com.jules.android.gui.databinding.FragmentHomeBinding

/**
 * Home Fragment - Main landing screen of the application
 * Displays welcome message and quick action cards
 */
class HomeFragment : Fragment() {
    
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupClickListeners()
    }
    
    private fun setupClickListeners() {
        binding.commandsCard.setOnClickListener {
            findNavController().navigate(R.id.navigation_commands)
        }
        
        binding.settingsCard.setOnClickListener {
            findNavController().navigate(R.id.navigation_settings)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
