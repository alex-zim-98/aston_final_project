package com.example.aston_final_project.presentation.view

enum class StatusToolbar(val value: Int) {
    DEFAULT(0),
    FILTER(1),
    SEARCH(2),
    DETAILS(3);

    companion object {
        fun fromValue(value: Int): StatusToolbar {
            return entries.find { it.value == value } ?: DEFAULT
        }
    }
}