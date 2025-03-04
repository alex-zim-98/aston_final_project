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
import com.example.aston_final_project.app.network.NetworkManager
import com.example.aston_final_project.app.util.showText
import com.example.aston_final_project.databinding.FragmentHeadlinesBinding
import com.example.aston_final_project.domain.entity.Article
import com.example.aston_final_project.domain.repository.LocalRepository
import com.example.aston_final_project.domain.repository.RemoteRepository
import com.example.aston_final_project.presentation.mapper.RequestMapper
import com.example.aston_final_project.presentation.mvp.HeadlinesPresenter
import com.example.aston_final_project.presentation.mvp.HeadlinesView
import com.example.aston_final_project.presentation.viewmodel.SearchState
import com.example.aston_final_project.presentation.viewmodel.SearchViewModel
import com.example.aston_final_project.presentation.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch
import moxy.MvpDelegate
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class HeadlinesFragment : BaseFragment<FragmentHeadlinesBinding>(), HeadlinesView {

    private val moxyDelegate = MvpDelegate(this)

    @InjectPresenter
    lateinit var presenter: HeadlinesPresenter

    @Inject
    lateinit var remoteRepository: RemoteRepository

    @Inject
    lateinit var localRepository: LocalRepository

    @Inject
    lateinit var networkManager: NetworkManager

    @Inject
    lateinit var requestMapper: RequestMapper

    @ProvidePresenter
    fun providePresenter(): HeadlinesPresenter {
        return HeadlinesPresenter(
            remoteRepository,
            localRepository,
            networkManager,
            requestMapper
        )
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val searchViewModel: SearchViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[SearchViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moxyDelegate.onCreate(savedInstanceState)
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
                    when (it) {
                        is SearchState.StartedChangeText -> {
                            if (it.text.length == 1) presenter.searchNews()
                        }

                        SearchState.EndChangeText -> {}
                    }
                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
        moxyDelegate.onAttach()
    }

    override fun onStop() {
        super.onStop()
        moxyDelegate.onDetach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        moxyDelegate.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        moxyDelegate.onDestroy()
    }

    companion object {
        fun newInstance() =
            HeadlinesFragment()
    }

    override fun getHeadlinesList(list: List<Article>) {
        Log.d("test", list.toString())
    }

    override fun showError(error: String) {
        error.showText(this.requireContext())
        Log.d("test", error)
    }
}