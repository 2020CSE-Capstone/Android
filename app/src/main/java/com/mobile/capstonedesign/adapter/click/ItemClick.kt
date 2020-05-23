package com.mobile.capstonedesign.adapter.click

import android.view.View

interface ItemClick {
    fun onClick(view: View, position: Int, no: Int)
}