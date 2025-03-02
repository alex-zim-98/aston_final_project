package com.example.aston_final_project.di

import com.example.aston_final_project.data.repository.RemoteRepositoryImpl
import com.example.aston_final_project.domain.repository.RemoteRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindRemoteRepository(repository: RemoteRepositoryImpl): RemoteRepository
}