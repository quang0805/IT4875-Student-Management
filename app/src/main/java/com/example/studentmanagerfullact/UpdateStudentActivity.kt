package com.example.studentmanagerfullact

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.studentmanagerfullact.R
import com.example.studentmanagerfullact.Student

class UpdateStudentActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtId: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPhone: EditText
    private lateinit var btnSave: Button
    private var position = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        edtName = findViewById(R.id.edtName)
        edtId = findViewById(R.id.edtId)
        edtEmail = findViewById(R.id.edtEmail)
        edtPhone = findViewById(R.id.edtPhone)
        btnSave = findViewById(R.id.btnSave)

        val student = intent.getSerializableExtra("student") as Student
        position = intent.getIntExtra("position", -1)

        edtName.setText(student.name)
        edtId.setText(student.studentId)
        edtEmail.setText(student.email)
        edtPhone.setText(student.phone)

        btnSave.setOnClickListener {
            val updatedStudent = Student(
                edtName.text.toString(),
                edtId.text.toString(),
                edtEmail.text.toString(),
                edtPhone.text.toString()
            )
            val resultIntent = Intent()
            resultIntent.putExtra("student", updatedStudent)
            resultIntent.putExtra("position", position)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}