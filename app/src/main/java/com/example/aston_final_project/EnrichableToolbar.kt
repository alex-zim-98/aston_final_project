package com.example.aston_final_project

import com.example.aston_final_project.views.BaseToolBarModel

interface EnrichableToolbar<MODEL : BaseToolBarModel> {
    fun enrich(model: MODEL)
}