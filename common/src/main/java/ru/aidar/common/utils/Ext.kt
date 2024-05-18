package ru.aidar.common.utils

import android.content.Context
import android.widget.Toast

fun Context.showToast(text: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, length).show()
}