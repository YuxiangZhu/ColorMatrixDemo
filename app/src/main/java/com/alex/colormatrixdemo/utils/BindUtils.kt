package com.alex.colormatrixdemo.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter

object BindUtils {
    @JvmStatic
    fun maskID(string: String): String {
        return "${string.take(6)}*******${string.takeLast(4)}"
    }

    @JvmStatic
    @BindingAdapter("android:src")
    fun setSrc(iv: ImageView, src: Int) {
        iv.setImageResource(src)
    }
}
