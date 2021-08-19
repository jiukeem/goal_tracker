package com.example.goaltracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.goaltracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.recyclerView?.adapter = TrackerTableAdapter(this)
        // 어댑터 설정은 여기서 해도 되고 xml에서 해도된다.
        // viewModel 에서 adapter = CalendarAdapter() 변수 하나 만들어놓고 binding?.calendarRecycler?.adapter = viewModel?.adapter 이렇게 해도 되고 방법은 다양하다.
        binding?.viewModel = MainViewModel()
    }
}