package com.example.aston_final_project.presentation.view.model

import javax.inject.Inject

data class DefaultToolbarModel @Inject constructor(
    override val title: String
): BaseToolBarModel(title)
