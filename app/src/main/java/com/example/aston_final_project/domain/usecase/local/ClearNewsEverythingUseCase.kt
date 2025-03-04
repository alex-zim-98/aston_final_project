package com.example.aston_final_project.domain.usecase.local

import com.example.aston_final_project.domain.repository.LocalRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class ClearNewsEverythingUseCase @Inject constructor(
    private val repository: LocalRepository
) {
    operator fun invoke(): Completable {
        return repository.clearNewsEverything()
    }
}