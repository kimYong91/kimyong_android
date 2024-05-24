package com.busanit.ch09_recycler_view.반복연습

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.busanit.ch09_recycler_view.R
import com.busanit.ch09_recycler_view.databinding.ActivityRecyclerBinding
import com.busanit.ch09_recycler_view.databinding.ItemBinding

data class Item(val text: String)
val itemList = mutableListOf<Item>()
class RecyclerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
class ItemAdapter(val itemList: MutableList<Item>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    inner class ItemHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind() {
            binding.run {
                deleteButton.setOnClickListener {
                    itemList.removeAt(adapterPosition)
                    notifyItemRemoved(adapterPosition)
                }
                linearView.setOnClickListener{
                    val intent = Intent(binding.root.context, DetailActivity::class.java)
                    binding.root.context.startActivity(intent)

                    intent.putExtra("Key", binding.text.text.toString())
                    binding.root.context.startActivity(intent)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}