package com.silk.client.utils

import android.content.Context

class Extension {
    companion object{
        fun Int.dpToPx(context: Context): Int {
            val scale = context.resources.displayMetrics.density
            return (this * scale + 0.5f).toInt()
        }
    }
}