package com.canque.midtermexamresub.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students_table")
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val fName: String,
    val lName: String,
    val photoId: Int,
    val phoneNum: String
)
