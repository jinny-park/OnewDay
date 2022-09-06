package com.onew.onewday.DiaryList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.onew.onewday.R

class DiaryListAdapter (private val dataSet: MutableList<DiaryModel>): RecyclerView.Adapter<DiaryListAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemDate: TextView = itemView.findViewById(R.id.date)
        var itemtitle: TextView = itemView.findViewById(R.id.title)
        var itemdetail: TextView = itemView.findViewById(R.id.content)
    }

    // 1. Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryListAdapter.MyViewHolder {
        // create a new view
        val cardView = LayoutInflater.from(parent.context).inflate(R.layout.diary_item, parent, false)
        return MyViewHolder(cardView)
    }

    // 2. Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.itemDate.setText(dataSet.get(position).date)
        holder.itemtitle.setText(dataSet.get(position).title)
        holder.itemdetail.setText(dataSet.get(position).content)
    }

    // 3. Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return dataSet.size
    }
}