package com.example.animallinkgame.adapter

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.animallinkgame.R
import com.example.animallinkgame.Utils
import com.example.animallinkgame.data.DataItem
import com.example.animallinkgame.data.ImageItem
import com.google.android.material.imageview.ShapeableImageView

class PickLayoutAdapter(
    private var listItem: ArrayList<Int>,
    private var rcvPickLayout: RecyclerView,
) : RecyclerView.Adapter<PickLayoutAdapter.ViewHolder>() {

    private var saveOldPos = -1
    private var selectedItems = mutableListOf<ImageItem>() // Pair of Drawable ID and Position
    private var board: Array<Array<Int>>
    init{
        board = create2DArray(listItem)
    }

    private fun create2DArray(listItem: List<Int>): Array<Array<Int>> {
        val cols = DataItem.NUM_COL_PICK_LAYOUT
        val rows = (listItem.size + cols - 1) / cols
        val board = Array(rows) { Array(cols) { 0 } }
        for (i in listItem.indices) {
            val row = i / cols
            val col = i % cols
            board[row][col] = listItem[i]
//            Log.i("dongdong", "create2DArray $row $col ${listItem[i]}")
        }
        return board
    }

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
        var line_connectz = itemView.findViewById<View>(R.id.line_connect_item)

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
            val imageItem = Utils.getCoordinates(position)
            imageItem.setDrawablesId(drawablesId)
            imageItem.setPosition(position)
            selectedItems.add(imageItem)
            if (selectedItems.size == 2) {
                val connectItem = Utils.calculateConnect(board, selectedItems)
                Log.i("dongdong", "check can connect =  ${connectItem.isConnectItem()} between ${selectedItems[0].getPosition()} and ${selectedItems[1].getPosition()}")
                if (connectItem.isConnectItemz && selectedItems[0].getDrawablesId() == selectedItems[1].getDrawablesId()) {
                    val viewHolder1 =
                        rcvPickLayout.findViewHolderForAdapterPosition(selectedItems[0].getPosition()) as PickLayoutAdapter.ViewHolder
                    val viewHolder2 =
                        rcvPickLayout.findViewHolderForAdapterPosition(selectedItems[1].getPosition()) as PickLayoutAdapter.ViewHolder
                    connectItem.getListPositionConnect()?.forEach {
                       val viewHolder = rcvPickLayout.findViewHolderForAdapterPosition(it) as PickLayoutAdapter.ViewHolder
                        viewHolder.line_connectz.visibility = View.VISIBLE
                        Handler(Looper.getMainLooper()).postDelayed({
                            viewHolder.line_connectz.visibility = View.GONE
                        }, DataItem.TIME_VISIBLE)
                    }
                    Handler(Looper.getMainLooper()).postDelayed({
                        viewHolder1.imageItem.visibility = View.GONE
                        viewHolder1.borderItem.visibility = View.GONE
                        viewHolder2.imageItem.visibility = View.GONE
                        viewHolder2.borderItem.visibility = View.GONE
                    }, DataItem.TIME_VISIBLE)
                    reset2Items()
                } else {
                    val viewHolder = rcvPickLayout.findViewHolderForAdapterPosition(
                        selectedItems.first().getPosition()
                    ) as PickLayoutAdapter.ViewHolder
                    viewHolder.borderItem.visibility = View.GONE
                    selectedItems.removeFirst()
                }
            }
        }
    }

    private fun reset2Items() {
        board[selectedItems[0].getRow()][selectedItems[0].getCol()] = 0
        board[selectedItems[1].getRow()][selectedItems[1].getCol()] = 0
        listItem[selectedItems[0].getPosition()] = 0
        listItem[selectedItems[1].getPosition()] = 0
        selectedItems.clear()
    }
}