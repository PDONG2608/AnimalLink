package com.example.animallinkgame

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class PickLayoutAdapter(private var listItem: List<Int>) : RecyclerView.Adapter<PickLayoutAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageItem : ShapeableImageView =  itemView.findViewById(R.id.item_pick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = View.inflate(parent.context, R.layout.item_pick_layout, null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.imageItem.setImageResource(listItem[position])
    }
}