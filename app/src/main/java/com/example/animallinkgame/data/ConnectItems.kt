package com.example.animallinkgame.data

class ConnectItems(val isConnectItemz: Boolean, val listPositionConnectz: ArrayList<Int>?) {
    fun isConnectItem(): Boolean {
        return isConnectItemz
    }

    fun getListPositionConnect(): ArrayList<Int>? {
        return listPositionConnectz
    }
}