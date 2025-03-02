package com.example.aston_final_project.presentation.viewmodel

sealed class SearchState {
    class StartedChangeText(val text: String) : SearchState()
    object EndChangeText : SearchState()
}