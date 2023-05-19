package com.example.peoplefind.presentation.util

import android.app.Activity
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Activity.showErrorDialog(title: String, message: String) {
    MaterialAlertDialogBuilder(this)
        .setTitle(title)
        .setMessage(message)
        .show()
}

fun Fragment.showErrorDialog(title: String, message: String) {
    MaterialAlertDialogBuilder(requireActivity())
        .setTitle(title)
        .setMessage(message)
        .show()
}