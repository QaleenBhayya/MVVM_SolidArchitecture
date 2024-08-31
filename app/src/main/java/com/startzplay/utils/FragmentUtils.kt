package com.startzplay.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

fun Fragment.initToolbar(toolbar: Toolbar, titleID: Int?, backEnable: Boolean, backIcon: Int?) {
    val appCompatActivity = activity as AppCompatActivity
    appCompatActivity.setSupportActionBar(toolbar)
    appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(backEnable)
    appCompatActivity.supportActionBar?.setHomeButtonEnabled(backEnable)

    if (titleID != null) {
        appCompatActivity.supportActionBar?.setTitle(titleID)
    }

    if (backIcon != null) {
        appCompatActivity.supportActionBar?.setHomeAsUpIndicator(backIcon)
    }
}