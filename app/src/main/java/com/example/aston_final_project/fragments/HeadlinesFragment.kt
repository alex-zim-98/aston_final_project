package com.example.aston_final_project.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.aston_final_project.databinding.FragmentHeadlinesBinding
import com.example.aston_final_project.viewmodel.SearchViewModel
import com.example.aston_final_project.viewmodel.ViewModelFactory
import javax.inject.Inject

class HeadlinesFragment : BaseFragment<FragmentHeadlinesBinding>() {
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
    ): FragmentHeadlinesBinding {
        return FragmentHeadlinesBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchViewModel.testLiveData.observe(viewLifecycleOwner) {
            Log.d("HeadlinesFragment", it.toString())
        }
    }

    companion object {
        fun newInstance() =
            HeadlinesFragment()
    }
}