package com.example.aston_final_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aston_final_project.databinding.FragmentSavedBinding

class SavedFragment : Fragment() {

    private var _binding: FragmentSavedBinding? = null
    private val binding
        get() = _binding ?: throw throw IllegalStateException("Binding is accessed after destroy")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavedBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() =
            SavedFragment()
    }
}