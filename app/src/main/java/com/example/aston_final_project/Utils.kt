package com.example.aston_final_project

import androidx.fragment.app.Fragment

fun replaceFragment(fragment: Fragment): Boolean {
    fragment.requireActivity().supportFragmentManager.beginTransaction()
        .replace(R.id.fragmentContainerView, fragment)
        .addToBackStack(null)
        .commit()
    return true
}