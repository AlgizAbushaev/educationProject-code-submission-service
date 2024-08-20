package ru.abushaev.codesubmissionservice.service.impl

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import ru.abushaev.codesubmissionservice.entity.CodeExecutionRequest
import ru.abushaev.codesubmissionservice.entity.CodeExecutionResponse
import ru.abushaev.codesubmissionservice.service.CodeExecutionServiceClient

@Service
class CodeExecutionServiceClientImpl(
    private val webClient: WebClient
): CodeExecutionServiceClient {
    override fun executeCode(code: String): Mono<CodeExecutionResponse> {
        return webClient.post()
            .uri("/execute")
            .bodyValue(CodeExecutionRequest(code))
            .retrieve()
            .bodyToMono(CodeExecutionResponse::class.java)
    }
}