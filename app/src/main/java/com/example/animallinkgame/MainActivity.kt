package com.example.animallinkgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animallinkgame.adapter.PickLayoutAdapter
import com.example.animallinkgame.data.DataItem
import com.example.animallinkgame.databinding.ActivityMainBinding
import com.example.animallinkgame.listener.ItemPickListener

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private lateinit var rcvPickLayout: RecyclerView
    private lateinit var mPickerAdapter: PickLayoutAdapter
    private lateinit var save2DrawableItem : ArrayList<Int>
    private lateinit var save2PositionItem : ArrayList<Int>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        hideStatusBar()
        val listItem = DataItem.randomList
        initAdapter(listItem)
        save2DrawableItem = arrayListOf()
        save2PositionItem = arrayListOf()
    }

    private fun hideStatusBar() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    private fun initAdapter(listItem: ArrayList<Int>) {
        rcvPickLayout = binding!!.rcvPickLayout
        rcvPickLayout.layoutManager = GridLayoutManager(this, DataItem.NUM_COL_PICK_LAYOUT)
        mPickerAdapter = PickLayoutAdapter(listItem, rcvPickLayout)
        rcvPickLayout.adapter = mPickerAdapter
    }
}