package com.example.aston_final_project

enum class Status(val value: Int) {
    DEFAULT(0),
    FILTER(1),
    SEARCH(2),
    DETAILS(3);

    companion object {
        fun fromValue(value: Int): Status {
            return entries.find { it.value == value } ?: DEFAULT
        }
    }
}