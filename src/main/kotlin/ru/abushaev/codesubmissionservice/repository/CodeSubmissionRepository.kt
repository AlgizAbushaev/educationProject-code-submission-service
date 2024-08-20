package ru.abushaev.codesubmissionservice.repository

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import ru.abushaev.codesubmissionservice.entity.CodeSubmission
import java.util.*

interface CodeSubmissionRepository: ReactiveCrudRepository<CodeSubmission, UUID> {
}