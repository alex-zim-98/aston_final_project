package com.example.aston_final_project.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
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
import com.example.aston_final_project.presentation.adapter.ArticlesAdapter
import com.example.aston_final_project.presentation.helper.ImageHelper
import com.example.aston_final_project.presentation.mapper.RequestMapper
import com.example.aston_final_project.presentation.mvp.HeadlinesPresenter
import com.example.aston_final_project.presentation.mvp.HeadlinesView
import com.example.aston_final_project.presentation.mvp.PresenterState
import com.example.aston_final_project.presentation.navigation.AppRouter
import com.example.aston_final_project.presentation.viewmodel.SearchState
import com.example.aston_final_project.presentation.viewmodel.SearchViewModel
import com.example.aston_final_project.presentation.viewmodel.ViewModelFactory
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import moxy.MvpDelegate
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

@OptIn(FlowPreview::class)
class HeadlinesFragment : BaseFragment<FragmentHeadlinesBinding>(), HeadlinesView {
    private lateinit var adapter: ArticlesAdapter

    private val moxyDelegate = MvpDelegate(this)

    @InjectPresenter
    lateinit var presenter: HeadlinesPresenter

    @Inject
    lateinit var imageHelper: ImageHelper

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

        presenter.setRestoreFragment(isConfigChanged)

        adapter = ArticlesAdapter(
            onClickAction = {  },
            onReachEnd = {
                presenter.onReachEnd()
            },
            imageHelper = imageHelper
        )

        binding.layoutArticles.recyclerView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                searchViewModel.searchState
                    .debounce(DELAY_TIME)
                    .collect {
                    when (it) {
                        is SearchState.StartedChangeText -> {
                            if (it.text.length == 1) {
                                presenter.searchNews()
                            } else {
                                adapter.filter(it.text)
                            }
                            adapter.setIsSearchingModeEnabled(true)
                            binding.tabLayout.isVisible = false
                        }

                        SearchState.EndChangeText -> {
                            presenter.restoreState()
                        }
                    }
                }
            }
        }

        presenter.loadNews(INITIAL_TAB_PAGE)

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

    override fun getHeadlinesList(list: List<Article>) {
        adapter.submitList(list)
    }

    override fun showError(error: String) {
        error.showText(this.requireContext())
        Log.d("test", error)
    }

    override fun fetchPresenterState(presenter: PresenterState) {
        binding.tabLayout.isVisible = true
        adapter.setIsSearchingModeEnabled(false)
        binding.progressBarHeadlines.isVisible = presenter.isLoading
        adapter.submitList(presenter.listTopHeadlines)
    }

    companion object {
        private const val INITIAL_TAB_PAGE = "general"
        private const val DELAY_TIME = 400L
        fun newInstance() =
            HeadlinesFragment()
    }

}