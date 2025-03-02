package com.example.aston_final_project.presentation.view

import com.example.aston_final_project.presentation.view.model.BaseToolBarModel

interface EnrichableToolbar<MODEL : BaseToolBarModel> {
    fun enrich(model: MODEL)
}