package com.mittylabs.sliderpickerexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mittylabs.sliderpickerlibrary.SliderLayoutManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv = findViewById<RecyclerView>(R.id.recycler_view)
        val tv = findViewById<TextView>(R.id.text)

        val sliderLayoutManager = SliderLayoutManager.Builder(this, RecyclerView.HORIZONTAL)
                .setInitialIndex(0)
                .setOnScrollListener(50L) { tv.text = it.toString() }
                .setScaling(SliderLayoutManager.Scaling.Logarithmic(0.6f))
                .build()

        rv.adapter = MainAdapter().apply {
            onItemClick = { sliderLayoutManager.smoothScroll(it) }
        }
        rv.layoutManager = sliderLayoutManager
    }
}