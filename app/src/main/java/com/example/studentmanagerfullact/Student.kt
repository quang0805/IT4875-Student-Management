package com.example.studentmanagerfullact

import java.io.Serializable

data class Student(
    var name: String,
    var studentId: String,
    var email: String,
    var phone: String
) : Serializable
