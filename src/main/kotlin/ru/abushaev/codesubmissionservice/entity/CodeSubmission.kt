package ru.abushaev.codesubmissionservice.entity

import org.springframework.data.annotation.Id
import java.time.LocalDateTime

data class CodeSubmission(
    @Id val id: Long? = null,
    val code: String,
    val status: SubmissionStatus,
    val output: String? = null,
    val error: String? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updateAt: LocalDateTime = LocalDateTime.now()
)
