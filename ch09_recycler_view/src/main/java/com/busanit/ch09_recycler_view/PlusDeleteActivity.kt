package com.busanit.ch09_recycler_view

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
import com.busanit.ch09_recycler_view.databinding.ActivityPlusDeleteBinding
import com.busanit.ch09_recycler_view.databinding.StudentDeleteItemBinding

class PlusDeleteActivity : AppCompatActivity() {

    data class Student(val name: String, val age: Int, val grade: Int)

    private lateinit var binding: ActivityPlusDeleteBinding
    private val studentList = mutableListOf<Student>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlusDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myAdapter = StudentAdapter(studentList)
        val myLayoutManager = LinearLayoutManager(this@PlusDeleteActivity)
        val myItemDirection = DividerItemDecoration(this@PlusDeleteActivity, DividerItemDecoration.VERTICAL)

        binding.run {
            recyclerView.apply {
                adapter = myAdapter
                layoutManager = myLayoutManager
                addItemDecoration(myItemDirection)
            }

            binding.addButton.setOnClickListener {
                val name = editTextName.text.toString()
                val age = editTextAge.text.toString().toInt()
                val grade = editTextGrade.text.toString().toInt()

                val student = Student(name, age, grade)
                studentList.add(student)
                myAdapter.notifyItemInserted(studentList.size - 1)

                binding.editTextName.text.clear()
                binding.editTextAge.text.clear()
                binding.editTextGrade.text.clear()

            }
        }

    }

    inner class StudentAdapter(
        private val studentList: MutableList<Student>
    ) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

        inner class StudentViewHolder(private val binding: StudentDeleteItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
            fun bind(student: Student) {
                binding.run {
                    textViewName.text = student.name
                    textViewAge.text = "${student.age}세"
                    textViewGrade.text = "${student.grade}점"

                    deleteButton.setOnClickListener {
                        val position = adapterPosition
                        studentList.removeAt(position)
                        notifyItemRemoved(position)
                    }
                }
            }
        }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
                val binding = StudentDeleteItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return StudentViewHolder(binding)
            }

            override fun getItemCount(): Int = studentList.size

            override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
                holder.bind(studentList[position])
            }
        }
}