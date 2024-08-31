package com.startzplay.base

interface BaseView {

    fun onShowLoader(show: Boolean)

    fun onShowError(errorMessage: String?)
}