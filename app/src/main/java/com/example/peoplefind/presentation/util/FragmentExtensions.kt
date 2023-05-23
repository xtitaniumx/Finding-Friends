package com.example.peoplefind.presentation.util

import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Fragment.showErrorDialog(title: String, message: String, onClick: () -> Unit) {
    MaterialAlertDialogBuilder(requireActivity())
        .setTitle(title)
        .setMessage(message)
        .setCancelable(false)
        .setNegativeButton("ОК") { dialog, which ->
            onClick()
        }
        .show()
}