package com.example.peoplefind.presentation.util

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import com.example.peoplefind.databinding.DialogInterestDescriptionBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Intent.clearStack() {
    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
}

fun Activity.showErrorDialog(title: String, message: String) {
    MaterialAlertDialogBuilder(this)
        .setTitle(title)
        .setMessage(message)
        .setCancelable(false)
        .show()
}

fun Activity.createInterestDialog(interest: String): Pair<MaterialAlertDialogBuilder, DialogInterestDescriptionBinding> {
    val dialogBuilder = MaterialAlertDialogBuilder(this)
    val dialogBinding = DialogInterestDescriptionBinding.inflate(layoutInflater).apply {
        textInterestName.text = interest
        dialogBuilder.setView(this.root)
    }
    return Pair(dialogBuilder, dialogBinding)
}