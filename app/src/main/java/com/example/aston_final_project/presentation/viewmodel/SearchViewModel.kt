package com.example.aston_final_project.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.aston_final_project.domain.repository.RemoteRepository
import com.example.aston_final_project.domain.usecase.SearchNewsUseCase
import com.example.aston_final_project.presentation.mapper.RequestMapper
import com.example.aston_final_project.presentation.viewmodel.request.FilteredNewsRequest
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    remoteRepository: RemoteRepository,
    private val requestMapper: RequestMapper
) : ViewModel() {
    val compositeDisposable = CompositeDisposable()
//    private val headlinesRequest = HeadlinesRequest(
//        category = "",
//        page = 10
//    )

    private val filteredNewsRequest = FilteredNewsRequest(
        from = "",
        to = "",
        language = "",
        sortBy = ""
    )

//    private val getTopHeadlinesUseCase = remoteRepository.getTopHeadlines(
//        requestMapper.headlinesRequestToMap(headlinesRequest)
//    )


    private val searchNewsUseCase = SearchNewsUseCase(remoteRepository)

    private val _searchState = MutableStateFlow<SearchState>(SearchState.EndChangeText)
    val searchState = _searchState.asStateFlow()
    fun sendTextQuery(state: SearchState) {
        when(state) {
            is SearchState.StartedChangeText -> {
                _searchState.value = SearchState.StartedChangeText(state.text)
                val disposable = searchNewsUseCase.invoke(
                    requestMapper.filteredNewsRequestToMap(filteredNewsRequest)
                )
                    .observeOn(Schedulers.io())
                    .subscribe(
                        {
                            Log.d("sadasd", it.toString())
                        },
                        {
                            Log.d("Error", it.toString())
                        }
                    )
                compositeDisposable.add(disposable)
            }

            SearchState.EndChangeText -> {
                _searchState.value = SearchState.EndChangeText
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}