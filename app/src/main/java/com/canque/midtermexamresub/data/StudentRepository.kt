package com.canque.midtermexamresub.data

import androidx.lifecycle.LiveData
import com.canque.midtermexamresub.models.Student

class StudentRepository(private val studentDao: StudentDao) {
    val readAllData: LiveData<List<Student>> = studentDao.readAllData()

    suspend fun addStudent(student: Student){
        studentDao.addStudent(student)
    }

    suspend fun deleteAllAndResetAutoIncrement() {
        studentDao.deleteAll()
        studentDao.resetAutoIncrement()
    }
}