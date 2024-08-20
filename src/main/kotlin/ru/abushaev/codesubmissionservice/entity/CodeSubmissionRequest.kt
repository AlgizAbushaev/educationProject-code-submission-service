package ru.abushaev.codesubmissionservice.entity

data class CodeSubmissionRequest(
    val taskId: String,
    val userId: String,
    val code: String
)
