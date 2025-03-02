package com.example.aston_final_project.presentation.search

import com.example.aston_final_project.domain.entity.Article
import com.example.aston_final_project.domain.repository.RemoteRepository
import com.example.aston_final_project.presentation.mapper.RequestMapper
import io.reactivex.rxjava3.core.Observable

class HeadlinesSearchSearchMode(
    private val remoteRepository: RemoteRepository,
    private val requestMapper: RequestMapper,
) : BaseSearchMode<Article>() {


    override suspend fun fetchDataList(): Observable<List<Article>> {
        return remoteRepository.searchNews(
            requestMapper.filteredNewsRequestToMap(filteredNewsRequest)
        )
    }
}