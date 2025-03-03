package com.example.aston_final_project.domain.usecase

import com.example.aston_final_project.domain.repository.LocalRepository
import javax.inject.Inject

class ClearNewsEverythingUseCase @Inject constructor(
    private val repository: LocalRepository
) {
    suspend operator fun invoke() {
        return repository.clearNewsEverything()
    }
}