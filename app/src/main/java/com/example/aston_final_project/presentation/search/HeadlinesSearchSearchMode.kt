package com.example.aston_final_project.presentation.search

import com.example.aston_final_project.domain.entity.Article
import com.example.aston_final_project.domain.repository.LocalRepository
import com.example.aston_final_project.domain.repository.RemoteRepository
import com.example.aston_final_project.domain.usecase.ClearNewsEverythingUseCase
import com.example.aston_final_project.domain.usecase.InsertNewsEverythingUseCase
import com.example.aston_final_project.domain.usecase.SearchNewsUseCase
import com.example.aston_final_project.presentation.mapper.RequestMapper
import io.reactivex.rxjava3.core.Single

class HeadlinesSearchSearchMode(
    remoteRepository: RemoteRepository,
    localRepository: LocalRepository,
    private val requestMapper: RequestMapper,
) : BaseSearchMode<Article>(), HeadlinesModeSearchable {
    private val searchNewsUseCase = SearchNewsUseCase(remoteRepository)
    private val insertNewsEverythingUseCase = InsertNewsEverythingUseCase(localRepository)
    private val clearNewsEverythingUseCase = ClearNewsEverythingUseCase(localRepository)
    override fun fetchDataList(): Single<List<Article>> {
        return searchNewsUseCase.invoke(
            requestMapper.filteredNewsRequestToMap(filteredNewsRequest)
        )
    }

    override suspend fun insertNewsEverything(response: List<Article>) {
        insertNewsEverythingUseCase.invoke(response)
    }

    override suspend fun clearNewsEverything() {
        clearNewsEverythingUseCase.invoke()
    }
}