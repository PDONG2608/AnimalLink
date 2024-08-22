package com.example.animallinkgame.data

class ImageItem(private var row: Int, private var col: Int) {
    private var drawablesId : Int = 0
    private var position : Int = 0

    fun setPosition(position: Int) {
        this.position = position
    }

    fun getPosition(): Int {
        return position
    }

    fun setDrawablesId(drawablesId: Int) {
        this.drawablesId = drawablesId
    }

    fun getDrawablesId(): Int {
        return drawablesId
    }
    fun getRow(): Int {
        return row
    }

    fun getCol(): Int {
        return col
    }
}