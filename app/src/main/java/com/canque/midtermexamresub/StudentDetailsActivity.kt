package com.canque.midtermexamresub

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.canque.midtermexamresub.constants.Constants
import com.canque.midtermexamresub.databinding.ActivityStudentDetailsBinding

class StudentDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding = ActivityStudentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val studentName = intent.getStringExtra(Constants.PARAM_FIRST_NAME) + " " + intent.getStringExtra(Constants.PARAM_LAST_NAME)
        val studentPhoto = intent.getIntExtra(Constants.PARAM_PHOTO_ID, 0)
        val studentId = intent.getIntExtra(Constants.PARAM_STUDENT_ID, 0)
        val studentPhoneNum = intent.getStringExtra(Constants.PARAM_PHONE_NUM)

        binding.name.setText(studentName)
        binding.studentId.setText(studentId.toString())
        binding.phoneNum.setText(studentPhoneNum)
        binding.imageView.setImageResource(studentPhoto)

        binding.callStudentBtn.setOnClickListener {
            /*val callIntent = Intent(Intent.ACTION_CALL).apply {
                data = Uri.parse("tel:$studentPhoneNum")
            }
            startActivity(callIntent)*/
            Toast.makeText(this, "Function not implemented yet.", Toast.LENGTH_SHORT).show()
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.callStudentBtn) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            val params = v.layoutParams as ConstraintLayout.LayoutParams
            params.leftMargin = insets.left
            params.bottomMargin = insets.bottom + 40
            params.rightMargin = insets.right + 40
            v.layoutParams = params

            v.requestLayout()

            WindowInsetsCompat.CONSUMED
        }
    }
}