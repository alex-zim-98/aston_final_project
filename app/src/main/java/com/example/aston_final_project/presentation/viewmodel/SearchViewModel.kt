package com.example.aston_final_project.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class SearchViewModel @Inject constructor() : ViewModel() {
    private val _searchState = MutableStateFlow<SearchState>(SearchState.EndChangeText)
    val searchState = _searchState.asStateFlow()
    fun sendTextQuery(state: SearchState) {
        when(state) {
            is SearchState.StartedChangeText -> {
                _searchState.value = SearchState.StartedChangeText(state.text)
            }

            SearchState.EndChangeText -> {
                _searchState.value = SearchState.EndChangeText
            }
        }
    }
}