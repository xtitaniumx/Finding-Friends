package com.example.peoplefind.presentation.util

import android.app.Activity
import android.content.Intent
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Intent.clearStack() {
    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
}

fun Activity.showErrorDialog(title: String, message: String) {
    MaterialAlertDialogBuilder(this)
        .setTitle(title)
        .setMessage(message)
        .setNegativeButton("ОК") { dialog, _ ->
            dialog.dismiss()
        }
        .show()
}

fun Activity.showErrorDialog(title: String, message: String, onClick: () -> Unit) {
    MaterialAlertDialogBuilder(this)
        .setTitle(title)
        .setMessage(message)
        .setCancelable(false)
        .setNegativeButton("ОК") { dialog, _ ->
            onClick()
            dialog.dismiss()
        }
        .show()
}