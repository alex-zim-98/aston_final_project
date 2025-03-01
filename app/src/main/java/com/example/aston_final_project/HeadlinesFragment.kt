package com.example.aston_final_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.aston_final_project.databinding.FragmentHeadlinesBinding
import com.example.aston_final_project.viewmodel.SearchViewModel
import com.example.aston_final_project.views.SearchToolbar
import com.example.aston_final_project.views.ToolbarController

class HeadlinesFragment : BaseFragment<FragmentHeadlinesBinding>() {
    lateinit var searchViewModel: SearchViewModel

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHeadlinesBinding {
        return FragmentHeadlinesBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //searchViewModel =
    }

    companion object {
        fun newInstance() =
            HeadlinesFragment()
    }
}