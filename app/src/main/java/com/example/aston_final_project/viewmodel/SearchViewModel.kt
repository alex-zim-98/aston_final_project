package com.example.aston_final_project.viewmodel

import androidx.lifecycle.ViewModel
import com.example.aston_final_project.SearchState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class SearchViewModel @Inject constructor() : ViewModel() {
    private val _searchState = MutableStateFlow<SearchState>(SearchState.StartedChangeText(""))
    val searchState = _searchState.asStateFlow()
    fun sendTextQuery(state: SearchState) {
        when(state) {
            is SearchState.StartedChangeText -> {
                _searchState.value = SearchState.StartedChangeText(state.text)
            }

            SearchState.EndChangeText -> {}
        }
    }
}