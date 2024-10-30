package com.silk.client.ui.custom_components

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager2.widget.ViewPager2

//class NonSwipeableViewPager @JvmOverloads constructor(
//    context: Context,
//    attrs: AttributeSet? = null
//) : ViewPager2(context, attrs) {
//
//    override fun onTouchEvent(ev: MotionEvent): Boolean {
//        // Menolak semua event sentuh pada ViewPager untuk menonaktifkan swipe
//        return false
//    }
//
//    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
//        // Menolak intercepting event agar swipe tidak bekerja
//        return false
//    }
//}