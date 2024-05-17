package ru.aidar.common.core.resources

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface JadroResourceManager {

    fun getString(
        @StringRes resource: Int,
    ): String

    fun getDrawable(
        @DrawableRes id: Int,
    ): Drawable?
}