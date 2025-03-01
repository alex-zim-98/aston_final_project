package com.example.aston_final_project.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.internal.Provider
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val creators: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = creators[modelClass]?.get()
        return requireNotNull(viewModel as T) {
            "ViewModel $modelClass not found"
        }
    }
}