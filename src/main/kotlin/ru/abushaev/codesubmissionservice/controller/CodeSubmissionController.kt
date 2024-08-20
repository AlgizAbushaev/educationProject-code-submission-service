package ru.abushaev.codesubmissionservice.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import ru.abushaev.codesubmissionservice.entity.CodeSubmissionRequest
import ru.abushaev.codesubmissionservice.entity.CodeSubmissionResponse
import ru.abushaev.codesubmissionservice.service.CodeSubmissionService

@RestController
@RequestMapping("api/submission/")
class CodeSubmissionController(private val codeSubmissionService: CodeSubmissionService) {

    @PostMapping("/submit-code")
    fun submitCode(@RequestBody requestMono: Mono<CodeSubmissionRequest>): Mono<ResponseEntity<CodeSubmissionResponse>> {
        return requestMono.flatMap { request ->
            if (request.code.isEmpty()) {
                Mono.just(ResponseEntity.badRequest().body(CodeSubmissionResponse("Ошибка: Код не может быть пустым")))
            } else codeSubmissionService.submitCode(request)
                .map { submissionId -> ResponseEntity.ok(CodeSubmissionResponse()) }
        }
    }
}