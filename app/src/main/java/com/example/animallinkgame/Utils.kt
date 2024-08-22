package com.example.animallinkgame

import com.example.animallinkgame.data.DataItem
import com.example.animallinkgame.data.ImageItem

class Utils {
    companion object {
        fun getCoordinates(position: Int): ImageItem {
            val row = position / DataItem.NUM_COL_PICK_LAYOUT
            val col = position % DataItem.NUM_COL_PICK_LAYOUT
            return ImageItem(row, col)
        }

        fun calculateConnect(
            board: Array<Array<Int>>,
            selectedItems: MutableList<ImageItem>
        ): Boolean {
            val queue = ArrayDeque<Pair<Int, Int>>()
            val visited =
                Array(DataItem.NUM_ROW_PICK_LAYOUT) { IntArray(DataItem.NUM_COL_PICK_LAYOUT) { 0 } }
            val dx = intArrayOf(-1, 1, 0, 0)
            val dy = intArrayOf(0, 0, -1, 1)
            val xStart = selectedItems[0].getRow()
            val yStart = selectedItems[0].getCol()
            queue.add(Pair(xStart, yStart))
            visited[xStart][yStart] = 0
            while (queue.isNotEmpty()) {
                val (x, y) = queue.removeFirst()
                for (i in 0 until 4) {
                    val xNext = x + dx[i]
                    val yNext = y + dy[i]
                    if (xNext == selectedItems[1].getRow() && yNext == selectedItems[1].getCol()) {
                        if (selectedItems[0].getDrawablesId() == selectedItems[1].getDrawablesId()) {
                            return true
                        }
                    }
                    if (xNext < 0 || xNext >= DataItem.NUM_ROW_PICK_LAYOUT || yNext < 0 || yNext >= DataItem.NUM_COL_PICK_LAYOUT) {
                        continue
                    }
                    if (board[xNext][yNext] == 0 && visited[xNext][yNext] == 0) {
                        queue.add(Pair(xNext, yNext))
                        visited[xNext][yNext] = visited[x][y] + 1
                    }
                }
            }
            return false
        }
    }
}