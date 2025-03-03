package com.example.aston_final_project.domain.usecase

import com.example.aston_final_project.domain.entity.Article
import com.example.aston_final_project.domain.repository.LocalRepository

class GetNewsEverythingByIdUseCase(private val repository: LocalRepository) {
    suspend operator fun invoke(id: String): Article {
        return repository.getNewsEverythingById(id)
    }
}