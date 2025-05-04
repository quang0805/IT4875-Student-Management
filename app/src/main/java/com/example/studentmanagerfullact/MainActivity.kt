package com.example.studentmanagerfullact

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var adapter: StudentAdapter
    private val students = mutableListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        fabAdd = findViewById(R.id.fabAdd)

        adapter = StudentAdapter(this, students) { action, student, position ->
            when (action) {
                "update" -> {
                    val intent = Intent(this, UpdateStudentActivity::class.java)
                    intent.putExtra("student", student)
                    intent.putExtra("position", position)
                    startActivityForResult(intent, 2)
                }
                "delete" -> {
                    AlertDialog.Builder(this)
                        .setTitle("Xác nhận xoá")
                        .setMessage("Bạn có chắc chắn muốn xoá sinh viên này không?")
                        .setPositiveButton("Xoá") { _, _ ->
                            students.removeAt(position)
                            adapter.notifyItemRemoved(position)
                            Toast.makeText(this, "Đã xoá sinh viên", Toast.LENGTH_SHORT).show()
                        }
                        .setNegativeButton("Huỷ", null)
                        .show()
                }
                "call" -> {
                    val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${student.phone}"))
                    startActivity(dialIntent)
                }
                "email" -> {
                    val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:${student.email}")
                        putExtra(Intent.EXTRA_SUBJECT, "Thông báo từ nhà trường")
                    }
                    startActivity(emailIntent)
                }
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        fabAdd.setOnClickListener {
            startActivityForResult(Intent(this, AddStudentActivity::class.java), 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            val student = data.getSerializableExtra("student") as Student
            if (requestCode == 1) {
                students.add(student)
                adapter.notifyItemInserted(students.size - 1)
            } else if (requestCode == 2) {
                val pos = data.getIntExtra("position", -1)
                if (pos != -1) {
                    students[pos] = student
                    adapter.notifyItemChanged(pos)
                }
            }
        }
    }
}