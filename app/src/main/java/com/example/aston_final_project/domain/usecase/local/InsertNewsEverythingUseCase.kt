package com.example.aston_final_project.domain.usecase.local

import com.example.aston_final_project.domain.entity.Article
import com.example.aston_final_project.domain.repository.LocalRepository
import io.reactivex.rxjava3.core.Completable

class InsertNewsEverythingUseCase(private val repository: LocalRepository) {
    operator fun invoke(news: List<Article>): Completable {
        return repository.insertNewsEverything(news)
    }
}