package com.startzplay.base

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import com.startzplay.utils.DialogUtils

abstract class BaseActivity : AppCompatActivity(), BaseView {


    private lateinit var mProcessDialog: Dialog

    fun initObjets() {
        mProcessDialog = DialogUtils.buildWaitingDialog(this)
        mProcessDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    override fun onShowLoader(show: Boolean) {
        if (show) {
            mProcessDialog.show()
        } else {
            mProcessDialog.dismiss()
        }

        if (!show) {
            mProcessDialog.setCancelable(true)
        }
    }

    override fun onShowError(errorMessage: String?) {

    }
}