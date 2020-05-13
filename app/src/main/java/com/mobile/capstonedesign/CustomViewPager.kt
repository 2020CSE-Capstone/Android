package com.mobile.capstonedesign

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2

class CustomViewPager(context: Context, attrs: AttributeSet): ViewPager(context, attrs) {
    var swipeEnabled = false

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return if (swipeEnabled) super.onTouchEvent(ev) else false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return if (swipeEnabled) super.onInterceptTouchEvent(ev) else false
    }

    override fun executeKeyEvent(event: KeyEvent): Boolean {
        return if (swipeEnabled) super.executeKeyEvent(event) else false
    }
}