package com.busanit.ch09_recycler_view.반복연습

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.busanit.ch09_recycler_view.databinding.ActivityRecyclerBinding
import com.busanit.ch09_recycler_view.databinding.ItemBinding

data class Item(val name: String, val age: String)
val itemList = mutableListOf<Item>()
class RecyclerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myAdapter = ItemAdapter(itemList)
        val myLayoutManager = LinearLayoutManager(this@RecyclerActivity)
        val myItemDecoration = DividerItemDecoration(this@RecyclerActivity, DividerItemDecoration.VERTICAL)

        binding.run {
            recyclerView.apply {
                layoutManager = myLayoutManager
                addItemDecoration(myItemDecoration)
                adapter = myAdapter
            }

            inputButton.setOnClickListener {
                val age = editAge.text.toString()
                val name = editName.text.toString()

                val itmes = Item(name, age)
                itemList.add(itmes)
                myAdapter.notifyItemInserted(itemList.size -1)
            }
        }

    }
}
class ItemAdapter(val itemList: MutableList<Item>) : RecyclerView.Adapter<ItemAdapter.ItemHolder>(){

    inner class ItemHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Item) {
            binding.run {
                deleteButton.setOnClickListener {
                    itemList.removeAt(adapterPosition)
                    notifyItemRemoved(adapterPosition)
                }
                textName.text = item.name
                textAge.text = item.age

            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemHolder(binding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(itemList[position])
    }

}