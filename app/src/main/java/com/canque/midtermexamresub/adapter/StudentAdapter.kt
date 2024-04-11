package com.canque.midtermexamresub.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.canque.midtermexamresub.StudentDetailsActivity
import com.canque.midtermexamresub.constants.Constants
import com.canque.midtermexamresub.databinding.ItemStudentBinding
import com.canque.midtermexamresub.models.Student

class StudentAdapter(
    private val activity: Activity,
    private val students: MutableList<Student>
): RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(
        private val activity: Activity,
        private val binding: ItemStudentBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(student: Student) {
            binding.studentPhoto.setImageResource(student.photoId)
            binding.studentNameTextView.text = student.fName + " " + student.lName
            binding.studentIdTextView.text = "ID: " + student.id.toString()
            binding.row.setOnClickListener {

                // Declaring Intent.
                val intent = Intent(
                    activity, // Context of the Current Activity.
                    StudentDetailsActivity::class.java // Activity that we want to open.
                )

                intent.putExtra(Constants.PARAM_STUDENT_ID, student.id)
                intent.putExtra(Constants.PARAM_PHOTO_ID, student.photoId)
                intent.putExtra(Constants.PARAM_FIRST_NAME, student.fName)
                intent.putExtra(Constants.PARAM_LAST_NAME, student.lName)
                intent.putExtra(Constants.PARAM_PHONE_NUM, student.phoneNum)

                // Open the ProfileActivity.
                activity.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentViewHolder {
        val binding = ItemStudentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StudentViewHolder(activity, binding)
    }

    override fun getItemCount() = students.size

    override fun onBindViewHolder(
        holder: StudentViewHolder,
        position: Int
    ) {
        holder.bind(students[position])
    }
}