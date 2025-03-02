package com.example.aston_final_project.presentation.mvp

import com.example.aston_final_project.app.network.NetworkManager
import com.example.aston_final_project.app.util.checkInternetConnection
import com.example.aston_final_project.domain.repository.RemoteRepository
import com.example.aston_final_project.presentation.mapper.RequestMapper
import com.example.aston_final_project.presentation.search.HeadlinesSearchSearchMode
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import moxy.MvpPresenter
import javax.inject.Inject

class HeadlinesPresenter @Inject constructor(
    remoteRepository: RemoteRepository,
    private val networkManager: NetworkManager,
    requestMapper: RequestMapper
) : MvpPresenter<HeadlinesView>() {
    private var isLoading = false
    private val compositeDisposable = CompositeDisposable()
    private val mutex = Mutex()
    private val headlinesEverythingList = HeadlinesSearchSearchMode(
        remoteRepository,
        requestMapper
    )

    fun searchNews() {
        if (isLoading) return
        isLoading = true
        networkManager.checkInternetConnection(
            doOnSuccess = { searchByInternet() },
            doOnFail = {}
        )
        searchByInternet()
    }

    private fun searchByInternet() {
        CoroutineScope(Dispatchers.IO).launch {
            mutex.withLock {
                val disposable = headlinesEverythingList.fetchDataList()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {
                            viewState.getHeadlinesList(it)
                        },
                        { viewState.showError(it.toString()) }
                    )
                compositeDisposable.add(disposable)
                isLoading = false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}