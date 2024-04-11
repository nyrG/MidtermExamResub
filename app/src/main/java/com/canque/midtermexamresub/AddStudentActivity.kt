package com.canque.midtermexamresub

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.NavUtils
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.canque.midtermexamresub.data.StudentViewModel
import com.canque.midtermexamresub.databinding.ActivityAddStudentBinding
import com.canque.midtermexamresub.databinding.ActivityStudentDetailsBinding
import com.canque.midtermexamresub.models.Student

class AddStudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddStudentBinding
    private lateinit var mStudentViewModel: StudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding = ActivityAddStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mStudentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)

        binding.addBtn.setOnClickListener {
            insertDataToDatabase(this)
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.actLabel) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            val params = v.layoutParams as ConstraintLayout.LayoutParams
            params.topMargin = insets.top
            v.layoutParams = params

            v.requestLayout()

            WindowInsetsCompat.CONSUMED
        }
    }

    private fun insertDataToDatabase(context: Context) {
        val firstName = binding.addFirstNameEt.text.toString()
        val lastName = binding.addLastNameEt.text.toString()
        val phoneNum = binding.addPhoneNumEt.text.toString()

        if(inputCheck(firstName, lastName, phoneNum)){
            // Create User Object
            val student = Student(
                0,
                firstName,
                lastName,
                R.mipmap.default_student,
                phoneNum
            )
            // Add Data to Database
            mStudentViewModel.addStudent(student)
            Toast.makeText(context, "Successfully added!", Toast.LENGTH_LONG).show()
            finish()
        }else{
            Toast.makeText(context, "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, phoneNum: String): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(phoneNum))
    }
}