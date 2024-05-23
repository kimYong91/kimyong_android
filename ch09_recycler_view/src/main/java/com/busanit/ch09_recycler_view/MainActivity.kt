package com.busanit.ch09_recycler_view

import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.busanit.ch09_recycler_view.databinding.ActivityMainBinding
import com.busanit.ch09_recycler_view.databinding.StudentItemBinding

// 학생 정보를 담는 데이터 모델
    data class Student(var name: String, var age: Int, var grade: Int)
    val studentList = mutableListOf<Student>()
class MainActivity : AppCompatActivity() {

    // 학생 정보를 저장하는 리스트

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)  // 만든 xml 파일을 실행 파일로 변환 해주는 코드

        binding.run {
            recyclerView.adapter = MyRecyclerAdapter()
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    DividerItemDecoration.VERTICAL
                )
            )

            addButton.setOnClickListener {
                val name = editTextName.text.toString()
                val age = editTextAge.text.toString().toInt()
                val grade = editTextGrade.text.toString().toInt()

                val student = Student(name, age, grade)
                studentList.add(student)
                recyclerView.adapter?.notifyDataSetChanged()

            }
        }
    }

    inner class MyRecyclerAdapter : RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>() {

        inner class MyViewHolder(val itemBinding: StudentItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
            var textViewName: TextView = itemBinding.textViewName
            var textViewAge: TextView = itemBinding.textViewAge
            var textViewGrade: TextView = itemBinding.textViewGrade
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val itemBinding = StudentItemBinding.inflate(layoutInflater, parent, false)
            return MyViewHolder(itemBinding)
        }

        override fun getItemCount(): Int = studentList.size

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val (name, age, grade) = studentList[position]
            holder.textViewName.text = name
            holder.textViewAge.text = "${age}세"
            holder.textViewGrade.text = "${grade}점"
        }

    }

}