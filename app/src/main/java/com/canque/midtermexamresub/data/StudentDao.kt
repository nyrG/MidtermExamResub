package com.canque.midtermexamresub.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.canque.midtermexamresub.models.Student

@Dao
interface StudentDao {
    @Query("SELECT * FROM students_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Student>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addStudent(student: Student)

    @Query("DELETE FROM students_table")
    suspend fun deleteAll()

    @Query("DELETE FROM sqlite_sequence WHERE name='students_table'")
    suspend fun resetAutoIncrement()
}