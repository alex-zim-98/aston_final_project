package com.example.aston_final_project.domain.usecase

import com.example.aston_final_project.domain.entity.Article
import com.example.aston_final_project.domain.repository.RemoteRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetTopHeadlinesUseCase @Inject constructor(
    private val repository: RemoteRepository
) {
    operator fun invoke(params: Map<String, String>): Observable<List<Article>> {
        return repository.getTopHeadlines(params)
    }
}