package com.example.aston_final_project.domain.usecase.local

import com.example.aston_final_project.domain.entity.Article
import com.example.aston_final_project.domain.repository.LocalRepository
import io.reactivex.rxjava3.core.Maybe

class GetNewsEverythingByIdUseCase(private val repository: LocalRepository) {
    operator fun invoke(id: String): Maybe<Article> {
        return repository.getNewsEverythingById(id)
    }
}