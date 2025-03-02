package com.example.aston_final_project.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.aston_final_project.R

fun replaceFragment(activity: FragmentActivity, fragment: Fragment): Boolean {
    activity.supportFragmentManager.beginTransaction()
        .replace(R.id.fragmentContainerView, fragment)
        .addToBackStack(null)
        .commit()
    return true
}