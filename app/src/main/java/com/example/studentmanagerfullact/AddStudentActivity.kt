package com.example.studentmanagerfullact

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.studentmanagerfullact.R
import com.example.studentmanagerfullact.Student

class AddStudentActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtId: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPhone: EditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        edtName = findViewById(R.id.edtName)
        edtId = findViewById(R.id.edtId)
        edtEmail = findViewById(R.id.edtEmail)
        edtPhone = findViewById(R.id.edtPhone)
        btnSave = findViewById(R.id.btnSave)

        btnSave.setOnClickListener {
            val student = Student(
                edtName.text.toString(),
                edtId.text.toString(),
                edtEmail.text.toString(),
                edtPhone.text.toString()
            )
            val resultIntent = Intent()
            resultIntent.putExtra("student", student)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}