package com.startzplay.utils

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.startzplay.R

object DialogUtils {

    fun buildWaitingDialog(context: Context): Dialog {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.custom_loader)
        dialog.setCancelable(false)
        return dialog
    }
}