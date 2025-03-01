package com.example.aston_final_project

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun replaceFragment(activity: FragmentActivity, fragment: Fragment): Boolean {
    activity.supportFragmentManager.beginTransaction()
        .replace(R.id.fragmentContainerView, fragment)
        .addToBackStack(null)
        .commit()
    return true
}