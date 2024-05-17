package ru.aidar.common.core.resources

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

class JadroResourceManagerImpl(
    private val context: Context,
) : JadroResourceManager {
    override fun getString(resource: Int): String {
        return context.getString(resource)
    }

    override fun getDrawable(id: Int): Drawable? {
        return ContextCompat.getDrawable(context, id)
    }
}
