package com.example.aston_final_project.presentation.search

import com.example.aston_final_project.domain.entity.Article
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface HeadlinesModeSearchable {
    fun insertNewsEverything(response: List<Article>, doOnError: (error: String) -> Unit): Completable
    fun clearNewsEverything(doOnError: (error: String) -> Unit): Completable
    fun getNewsEverythingUseCase(doOnSuccess: (articles: List<Article>) -> Unit, doOnError: (error: String) -> Unit): Single<List<Article>>
}