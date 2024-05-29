package com.example.room

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.room.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "myroomdb.db"
        ).build()

        binding.run {
            buttonSave.setOnClickListener {
                val name = editTextName.text.toString()
                val age = editTextAge.text.toString().toIntOrNull()

                if (age == null) {
                    Toast.makeText(this@MainActivity, "나이를 잘 못 입력하셨습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    // 1. User 객체 생성
                    val user = User(name = name, age = age)

                    // 비동기 작업을 위한 코틀린 코루틴 스코프 (DB작업)
                    lifecycleScope.launch {
                        db.userDao().insert(user)   // 2. DB에 삽입
                    }
                }
            }

            buttonLoad.setOnClickListener {
                lifecycleScope.launch {
                    val userList = db.userDao().getAll()
                    val name = editTextName.text.toString()
                    val age = editTextAge.text.toString().toInt()


                }
            }
        }
    }
}

class RoomAdapter(val users: MutableList<User>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}