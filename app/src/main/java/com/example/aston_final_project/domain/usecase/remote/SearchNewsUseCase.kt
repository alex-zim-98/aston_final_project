package com.example.aston_final_project.domain.usecase.remote

import com.example.aston_final_project.domain.entity.Article
import com.example.aston_final_project.domain.repository.RemoteRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor(
    private val repository: RemoteRepository
) {
    operator fun invoke(params: Map<String, String>): Single<List<Article>> {
        return repository.searchNews(params)
    }
}