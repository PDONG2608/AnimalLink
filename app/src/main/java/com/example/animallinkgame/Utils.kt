package com.example.animallinkgame

import android.util.Log
import com.example.animallinkgame.data.ConnectItems
import com.example.animallinkgame.data.DataItem
import com.example.animallinkgame.data.ImageItem

class Utils {
    companion object {
        fun getCoordinates(position: Int): ImageItem {
            val row = position / DataItem.NUM_COL_PICK_LAYOUT
            val col = position % DataItem.NUM_COL_PICK_LAYOUT
            return ImageItem(row, col)
        }

        fun getPosition(row: Int, col: Int): Int {
            return row * DataItem.NUM_COL_PICK_LAYOUT + col
        }

        fun calculateConnect(
            board: Array<Array<Int>>,
            selectedItems: MutableList<ImageItem>
        ): ConnectItems {
            val listPositionConnect = ArrayList<Int>()
            val queue = ArrayDeque<Pair<Int, Int>>()
            val visited =
                Array(DataItem.NUM_ROW_PICK_LAYOUT) { IntArray(DataItem.NUM_COL_PICK_LAYOUT) { -1 } }
            val dx = intArrayOf(-1, 1, 0, 0)
            val dy = intArrayOf(0, 0, -1, 1)
            val xStart = selectedItems[0].getRow()
            val yStart = selectedItems[0].getCol()
            val xTarget = selectedItems[1].getRow()
            val yTarget = selectedItems[1].getCol()

            queue.add(Pair(xStart, yStart))
            visited[xStart][yStart] = 0

            while (queue.isNotEmpty()) {
                val (x, y) = queue.removeFirst()
                for (i in 0 until 4) {
                    val xNext = x + dx[i]
                    val yNext = y + dy[i]

                    if (xNext == xTarget && yNext == yTarget) {
                        visited[xNext][yNext] = visited[x][y] + 1
                        listPositionConnect.add(getPosition(xNext, yNext))

                        // Backtrack to find the full path
                        var currentX = x
                        var currentY = y
                        while (visited[currentX][currentY] != 0) {
                            listPositionConnect.add(getPosition(currentX, currentY))
                            for (j in 0 until 4) {
                                val xPrev = currentX - dx[j]
                                val yPrev = currentY - dy[j]
                                if (xPrev >= 0 && xPrev < DataItem.NUM_ROW_PICK_LAYOUT &&
                                    yPrev >= 0 && yPrev < DataItem.NUM_COL_PICK_LAYOUT &&
                                    visited[xPrev][yPrev] == visited[currentX][currentY] - 1) {
                                    currentX = xPrev
                                    currentY = yPrev
                                    break
                                }
                            }
                        }
                        listPositionConnect.add(getPosition(xStart, yStart))
                        listPositionConnect.reverse()
                        return ConnectItems(true, listPositionConnect)
                    }
                    if (xNext < 0 || xNext >= DataItem.NUM_ROW_PICK_LAYOUT || yNext < 0 || yNext >= DataItem.NUM_COL_PICK_LAYOUT) {
                        continue
                    }
                    if (board[xNext][yNext] == 0 && visited[xNext][yNext] == -1) {
                        queue.add(Pair(xNext, yNext))
                        visited[xNext][yNext] = visited[x][y] + 1
                    }
                }
            }
            return ConnectItems(false, null)
        }

    }
}