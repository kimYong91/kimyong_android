package com.example.room

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.room.databinding.UserBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserAdapter(val dao: UserDao) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var userList = listOf<User>()
    inner class UserViewHolder(val binding: UserBinding) : RecyclerView.ViewHolder(binding.root) {
           fun bind(user: User){
               binding.run {
                   textName.text = user.name
                   textAge.text = user.age.toString()

                   buttonDelete.setOnClickListener {
                       CoroutineScope(Dispatchers.IO).launch {
                           dao.delete(user)
                           userList = dao.getAll()

                           CoroutineScope(Dispatchers.Main).launch {
                               notifyDataSetChanged()
                           }
                       }
                   }
               }
           }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        TODO("Not yet implemented")
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}