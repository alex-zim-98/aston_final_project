package com.example.aston_final_project.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.aston_final_project.databinding.FragmentSavedBinding
import com.example.aston_final_project.presentation.viewmodel.SearchViewModel
import com.example.aston_final_project.presentation.viewmodel.ViewModelFactory
import javax.inject.Inject

class SavedFragment : BaseFragment<FragmentSavedBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val searchViewModel: SearchViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[SearchViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSavedBinding {
        return FragmentSavedBinding.inflate(inflater, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        fun newInstance() =
            SavedFragment()
    }
}