package com.example.studentmanagerfullact

import android.content.Context
import android.view.*
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanagerfullact.R
import com.example.studentmanagerfullact.Student

class StudentAdapter(
    private val context: Context,
    private val students: List<Student>,
    private val onItemAction: (String, Student, Int) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvStudentId: TextView = itemView.findViewById(R.id.tvStudentId)
        val btnMenu: ImageButton = itemView.findViewById(R.id.btnMenu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun getItemCount(): Int = students.size

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.tvName.text = student.name
        holder.tvStudentId.text = student.studentId

        holder.btnMenu.setOnClickListener {
            val popup = PopupMenu(context, holder.btnMenu)
            popup.menuInflater.inflate(R.menu.menu_student, popup.menu)
            popup.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menu_call -> {
                        onItemAction("call", student, position)
                        true
                    }
                    R.id.menu_email -> {
                        onItemAction("email", student, position)
                        true
                    }
                    R.id.menu_update -> {
                        onItemAction("update", student, position)
                        true
                    }
                    R.id.menu_delete -> {
                        onItemAction("delete", student, position)
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }
    }
}