package com.mittylabs.sliderpickerexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private val items: MutableList<Int> = ArrayList()

    var onItemClick: ((Int) -> Unit)? = null

    init {
        for (i in 1..100) {
            items.add(i)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_list_item, parent, false)
    )

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {}

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener { onItemClick?.invoke(adapterPosition) }
        }
    }
}