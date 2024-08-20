package com.example.animallinkgame.data

import com.example.animallinkgame.R
import kotlin.random.Random

object DataItem {
    private val listItem = arrayListOf(
        R.drawable.caption,
        R.drawable.drdoom,
        R.drawable.groot,
        R.drawable.hulk,
        R.drawable.ironman,
        R.drawable.thor
    )
    val randomList = generateRandomList(listItem, 48)
    private fun generateRandomList(drawableList: List<Int>, totalItems: Int): List<Int> {
        val itemsPerDrawable = totalItems / drawableList.size
        val remainder = totalItems % drawableList.size

        // Create a list where each drawable appears itemsPerDrawable times
        val resultList = mutableListOf<Int>()
        for (drawable in drawableList) {
            resultList.addAll(List(itemsPerDrawable) { drawable })
        }

        // Add the remainder items randomly
        val random = Random(System.currentTimeMillis())
        val drawableListWithRemainder = resultList.toMutableList()
        for (i in 0 until remainder) {
            drawableListWithRemainder.add(drawableList[random.nextInt(drawableList.size)])
        }

        // Shuffle the list to randomize the order
        drawableListWithRemainder.shuffle(random)
        return drawableListWithRemainder
    }
}