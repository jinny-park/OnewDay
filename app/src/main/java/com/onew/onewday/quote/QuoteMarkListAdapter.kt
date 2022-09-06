package com.onew.onewday.quote

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.onew.onewday.DiaryList.DiaryListAdapter
import com.onew.onewday.DiaryList.DiaryModel
import com.onew.onewday.R

class QuoteMarkListAdapter  (private val dataSet: MutableList<QuoteModel>): RecyclerView.Adapter<QuoteMarkListAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemQuote: TextView = itemView.findViewById(R.id.quote)
    }

    // 1. Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteMarkListAdapter.MyViewHolder {
        // create a new view
        val cardView = LayoutInflater.from(parent.context).inflate(R.layout.quote_item, parent, false)
        return MyViewHolder(cardView)
    }

    // 2. Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.itemQuote.setText(dataSet.get(position).quote)
    }

    // 3. Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return dataSet.size
    }
}