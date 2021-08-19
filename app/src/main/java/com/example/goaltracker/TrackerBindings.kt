package com.example.goaltracker

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter

object TrackerBindings {

    @JvmStatic
    @BindingAdapter("setDayImage")
    fun bindDayImage(day: ImageView, isImageInserted: Boolean) {
        Log.d("bindDayImage", "binding is working fine")
        if (isImageInserted) {
            day.setImageResource(R.drawable.tracker_icon_done)
        } else {
            day.setImageResource(0)
        }
    }
}