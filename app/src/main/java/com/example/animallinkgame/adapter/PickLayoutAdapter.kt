package com.example.animallinkgame.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.animallinkgame.R
import com.example.animallinkgame.listener.ItemPickListener
import com.google.android.material.imageview.ShapeableImageView

class PickLayoutAdapter(
    private var listItem: List<Int>,
    private var rcvPickLayout: RecyclerView,
) : RecyclerView.Adapter<PickLayoutAdapter.ViewHolder>() {

    private lateinit var mItemPickListener: ItemPickListener
    private var saveOldPos = -1
    private var selectedItems = mutableListOf<Pair<Int, Int>>() // Pair of Drawable ID and Position

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pick_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItem[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var imageItem: ShapeableImageView = itemView.findViewById(R.id.item_pick)
        var borderItem: ShapeableImageView = itemView.findViewById(R.id.border_item_pick)

        fun bind(drawablesId: Int) {
            imageItem.setImageResource(drawablesId)
            imageItem.setOnClickListener {
                borderItem.visibility = View.VISIBLE
                handleItemClick(drawablesId, layoutPosition)
            }
        }

        private fun handleItemClick(drawablesId: Int, position: Int) {
            if(saveOldPos == position){
                return
            }
            saveOldPos = position
            selectedItems.add(Pair(drawablesId, position))
            if (selectedItems.size == 2) {
                if (selectedItems[0].first == selectedItems[1].first) {
                    val viewHolder1 = rcvPickLayout.findViewHolderForAdapterPosition(selectedItems[0].second) as PickLayoutAdapter.ViewHolder
                    val viewHolder2 = rcvPickLayout.findViewHolderForAdapterPosition(selectedItems[1].second) as PickLayoutAdapter.ViewHolder
                    viewHolder1.itemView.visibility = View.GONE
                    viewHolder2.itemView.visibility = View.GONE
                    selectedItems.clear()
                } else {
                    val viewHolder = rcvPickLayout.findViewHolderForAdapterPosition(selectedItems.first().second) as PickLayoutAdapter.ViewHolder
                    viewHolder.borderItem.visibility = View.GONE
                    selectedItems.removeFirst()
                }
            }
        }
    }
}