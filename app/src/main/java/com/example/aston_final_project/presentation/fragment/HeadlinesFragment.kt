package com.example.aston_final_project.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.aston_final_project.presentation.viewmodel.SearchState
import com.example.aston_final_project.databinding.FragmentHeadlinesBinding
import com.example.aston_final_project.presentation.viewmodel.SearchViewModel
import com.example.aston_final_project.presentation.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch
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

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                searchViewModel.searchState.collect {
                    when(it) {
                        is SearchState.StartedChangeText -> Log.d("HeadlinesFragment", "entered")
                        SearchState.EndChangeText -> {}
                    }
                }
            }
        }

    }

    companion object {
        fun newInstance() =
            HeadlinesFragment()
    }
}