package com.example.aston_final_project.views

import javax.inject.Inject

data class DefaultToolbarModel @Inject constructor(
    override val title: String
): BaseToolBarModel(title)
