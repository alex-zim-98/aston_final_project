package com.example.aston_final_project.domain.usecase.local

import com.example.aston_final_project.domain.entity.Article
import com.example.aston_final_project.domain.repository.LocalRepository
import io.reactivex.rxjava3.core.Single

class GetNewsEverythingUseCase(private val repository: LocalRepository) {
    operator fun invoke(): Single<List<Article>> {
        return repository.getNewsEverything()
    }
}