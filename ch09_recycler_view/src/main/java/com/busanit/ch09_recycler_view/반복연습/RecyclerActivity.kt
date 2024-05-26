package com.busanit.ch09_recycler_view.반복연습

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.busanit.ch09_recycler_view.R
import com.busanit.ch09_recycler_view.databinding.ActivityDetailBinding
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
        val myItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        binding.run {
            recyclerView.apply {
                layoutManager = myLayoutManager
                addItemDecoration(myItemDecoration)
                adapter = myAdapter
            }

            inputButton.setOnClickListener {
                val age = editAge.text.toString()
                val name = editName.text.toString()

                val itmes = Item(age, name)
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
                linearView.setOnClickListener{
                    val intent = Intent(binding.root.context, DetailActivity::class.java)
                    binding.root.context.startActivity(intent)

                    intent.putExtra("Key", binding.textName.text.toString())
                    intent.putExtra("Key", binding.textAge.text.toString())
                    binding.root.context.startActivity(intent)
                }

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