package ru.abushaev.codesubmissionservice.service

import reactor.core.publisher.Mono
import ru.abushaev.codesubmissionservice.entity.CodeSubmission
import ru.abushaev.codesubmissionservice.entity.CodeSubmissionRequest
import ru.abushaev.codesubmissionservice.entity.CodeSubmissionResponse

interface CodeSubmissionService {
    fun submitCode(request: CodeSubmissionRequest): Mono<CodeSubmission>
}