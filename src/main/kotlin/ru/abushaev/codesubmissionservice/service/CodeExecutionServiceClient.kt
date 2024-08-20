package ru.abushaev.codesubmissionservice.service

import reactor.core.publisher.Mono
import ru.abushaev.codesubmissionservice.entity.CodeExecutionResponse

interface CodeExecutionServiceClient {
    fun executeCode(code: String): Mono<CodeExecutionResponse>
}