package com.example.animallinkgame.data

import com.example.animallinkgame.R
import kotlin.random.Random

object DataItem {
    const val NUM_COL_PICK_LAYOUT = 8
    const val NUM_ROW_PICK_LAYOUT = 6
    private val listItem = arrayListOf(
        R.drawable.caption,
        R.drawable.drdoom,
        R.drawable.groot,
        R.drawable.hulk,
        R.drawable.ironman,
        R.drawable.thor,
        R.drawable.drstrange,
        R.drawable.loki,
        R.drawable.thanos,
        R.drawable.blackwindow,
        R.drawable.piterpacker,
        R.drawable.challa,
        R.drawable.piterpacker1
    )
    val randomList = generateRandomList(listItem, 48)
    private fun generateRandomList(drawableList: ArrayList<Int>, totalItems: Int): ArrayList<Int> {
        val itemsPerDrawable = totalItems / drawableList.size
        val remainder = totalItems % drawableList.size

        // Create a list where each drawable appears itemsPerDrawable times
        val resultList = ArrayList<Int>()
        for (drawable in drawableList) {
            resultList.addAll(List(itemsPerDrawable) { drawable })
        }

        // Add the remainder items randomly
        val random = Random(System.currentTimeMillis())
        for (i in 0 until remainder) {
            resultList.add(drawableList[random.nextInt(drawableList.size)])
        }

        // Shuffle the list to randomize the order
        resultList.shuffle(random)
        return resultList
    }
}