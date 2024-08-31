package com.startzplay.base

import androidx.fragment.app.Fragment
import com.startzplay.ui.SPActivity

abstract class BaseFragment(layout: Int) : Fragment(layout), BaseView {


    val baseActivity: SPActivity? by lazy {
        if (activity is SPActivity) {
            activity as SPActivity
        } else {
            null
        }
    }

    override fun onShowLoader(show: Boolean) {
        baseActivity?.onShowLoader(show)
    }

    override fun onShowError(errorMessage: String?) {
        baseActivity?.onShowError(errorMessage)
    }
}