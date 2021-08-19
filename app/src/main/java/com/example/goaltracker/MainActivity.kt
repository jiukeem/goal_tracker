package com.example.goaltracker

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.goaltracker.databinding.ActivityMainBinding
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    lateinit var prefs: SharedPreferences
    private val daysList =  mutableListOf<Day>().apply {
        for (i in 1..35) {
            this.add(Day())
        }
    }
    private val DAYS_LIST = "daysList"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.viewModel = MainViewModel()

        val adapter = TrackerTableAdapter(this)
        binding?.recyclerView?.adapter = adapter

        prefs = this.getPreferences(0)

        loadDaysInfo()
        adapter.addWholeData(daysList)
    }

    override fun onStop() {
        super.onStop()
        saveDaysInfo()
    }

    private fun loadDaysInfo() {
        val savedInfo = prefs.getString(DAYS_LIST, "")
        if (savedInfo != "") {
            daysList.clear()
            daysList.addAll(savedInfo!!.split(",")
                .map { Day(isImageInserted = it == "true") })
        }
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }

    private fun saveDaysInfo() {
        val jsonArray = JSONArray()
        for (day in daysList) {
            jsonArray.put(day.isImageInserted)
        }
        val editor = prefs.edit()
        editor.putString(DAYS_LIST, jsonArray.toString())
        editor.apply()
    }

}