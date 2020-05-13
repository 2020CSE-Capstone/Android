package com.mobile.capstonedesign.ui.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.mobile.capstonedesign.R

//class DisallowInterceptRecyclerView : RecyclerView { // 일부 생략
//    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
//// 해당 뷰페이저에만 터치 이벤트 발생, 부모 뷰그룹은 이벤트 Block
//        parent.requestDisallowInterceptTouchEvent(true)
//        return super.dispatchTouchEvent(ev)
//    }
//}
