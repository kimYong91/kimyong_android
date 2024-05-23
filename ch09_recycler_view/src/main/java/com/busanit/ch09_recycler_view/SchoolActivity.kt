package com.busanit.ch09_recycler_view

import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.busanit.ch09_recycler_view.databinding.ActivitySchoolBinding
import com.busanit.ch09_recycler_view.databinding.StudentItemBinding

data class Students(val name: String, val age: Int, val grade: Int)
val studentsList = mutableListOf<Students>()
class SchoolActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySchoolBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            recyclerView.adapter = RecyclerAdapter()
            recyclerView.layoutManager = LinearLayoutManager(this@SchoolActivity)
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    this@SchoolActivity,
                    DividerItemDecoration.VERTICAL
                )
            )

            saveButton.setOnClickListener {
                val name = editName.text.toString()
                val age = editAge.text.toString().toInt()
                val grade = editGrade.text.toString().toInt()

                val student = Students(name, age, grade)

                studentsList.add(student)
                recyclerView.adapter?.notifyItemInserted(studentsList.size - 1)
            }

        }

    }
    inner class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

        inner class ViewHolder(val itemBinding: StudentItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
            var textName: TextView = itemBinding.textViewName
            var textAge: TextView = itemBinding.textViewAge
            var textGrade: TextView = itemBinding.textViewGrade
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemBinding = StudentItemBinding.inflate(layoutInflater, parent, false)
            return ViewHolder(itemBinding)
        }

        override fun getItemCount(): Int = studentsList.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val (name, age, grade) = studentsList[position]
            holder.textName.text = name
            holder.textAge.text = "${age}세"
            holder.textGrade.text = "${grade}점"
        }

    }
}
