package com.example.animallinkgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.animallinkgame.data.DataItem
import com.example.animallinkgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        val listItem = DataItem.randomList
        initAdapter(listItem)
    }

    private fun initAdapter(listItem: List<Int>) {
        val rcvPickLayout =  binding!!.rcvPickLayout
        rcvPickLayout.layoutManager = GridLayoutManager(this, 8)
        rcvPickLayout.adapter = PickLayoutAdapter(listItem)
    }
}