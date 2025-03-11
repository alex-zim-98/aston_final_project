package com.example.aston_final_project.presentation.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.aston_final_project.databinding.FragmentFilterBinding

class FilterFragment : BaseFragment<FragmentFilterBinding>() {

    companion object {
        fun newInstance() = FilterFragment()
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFilterBinding {
        return FragmentFilterBinding.inflate(inflater, container, false)
    }
}