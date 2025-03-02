package com.example.aston_final_project

sealed class SearchState {
    class StartedChangeText(val text: String) : SearchState()
    object EndChangeText : SearchState()
}