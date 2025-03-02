package com.example.aston_final_project.views

interface EnrichableToolbar<MODEL : BaseToolBarModel> {
    fun enrich(model: MODEL)
}