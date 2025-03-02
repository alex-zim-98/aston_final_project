package com.example.aston_final_project.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aston_final_project.databinding.FragmentSourceBinding

class SourceFragment : Fragment() {
    private var _binding: FragmentSourceBinding? = null
    private val binding
        get() = _binding ?: throw throw IllegalStateException("Binding is accessed after destroy")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSourceBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        fun newInstance() =
            SourceFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}