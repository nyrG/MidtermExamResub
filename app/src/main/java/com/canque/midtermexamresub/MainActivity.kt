package com.canque.midtermexamresub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.canque.midtermexamresub.adapter.StudentAdapter
import com.canque.midtermexamresub.data.StudentViewModel
import com.canque.midtermexamresub.databinding.ActivityMainBinding
import com.canque.midtermexamresub.models.Student

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mStudentViewModel: StudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addStudent.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)
        }

        mStudentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)
        mStudentViewModel.readAllData.observe(this, Observer { student ->
            binding.studentList.adapter = StudentAdapter(
                this, // Adding this parameter since we need the context of the current screen.
                student.toMutableList()
            )
        })
        binding.studentList.layoutManager = LinearLayoutManager(this)


        ViewCompat.setOnApplyWindowInsetsListener(binding.addStudent) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            val params = v.layoutParams as ConstraintLayout.LayoutParams
            params.leftMargin = insets.left
            params.bottomMargin = insets.bottom + 40
            params.rightMargin = insets.right + 40
            v.layoutParams = params

            v.requestLayout()

            WindowInsetsCompat.CONSUMED
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.studentList) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            val params = v.layoutParams as ConstraintLayout.LayoutParams
            params.bottomMargin = insets.bottom
            v.layoutParams = params

            v.requestLayout()

            WindowInsetsCompat.CONSUMED
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.constraintLayout) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            val params = v.layoutParams as ConstraintLayout.LayoutParams
            params.topMargin = insets.top
            v.layoutParams = params

            v.requestLayout()

            WindowInsetsCompat.CONSUMED
        }
    }
}