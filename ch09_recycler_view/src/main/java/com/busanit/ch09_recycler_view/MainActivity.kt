package com.busanit.ch09_recycler_view

import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.busanit.ch09_recycler_view.databinding.ActivityMainBinding
import com.busanit.ch09_recycler_view.databinding.StudentItemBinding

// 학생 정보를 담는 데이터 모델
    data class Student(var name: String, var age: Int, var grade: Int)
class MainActivity : AppCompatActivity() {

    // 학생 정보를 저장하는 리스트
    val studentList = mutableListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)  // 만든 xml 파일을 실행 파일로 변환 해주는 코드


    }

    inner class MyRecyclerAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        inner class MyViewHolder(val itemBinding: StudentItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
            // 각 View의 주소값을 담을 변수
            var textViewName: TextView
            var textViewAge: TextView
            var textViewGrade: TextView

            // 뷰홀더가 생성될 때 뷰 바인딩을 통해 초기화
            init {
                textViewName = itemBinding.textViewName
                textViewAge = itemBinding.textViewAge
                textViewGrade = itemBinding.textViewGrade
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val itemBinding = StudentItemBinding.inflate(layoutInflater, parent, false)
            return MyViewHolder(itemBinding)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            TODO("Not yet implemented")
        }

        override fun getItemCount(): Int {
            TODO("Not yet implemented")
        }
    }
}