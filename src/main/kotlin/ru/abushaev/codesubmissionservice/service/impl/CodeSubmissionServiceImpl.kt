package ru.abushaev.codesubmissionservice.service.impl

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import ru.abushaev.codesubmissionservice.entity.CodeSubmission
import ru.abushaev.codesubmissionservice.entity.CodeSubmissionRequest
import ru.abushaev.codesubmissionservice.entity.CodeSubmissionResponse
import ru.abushaev.codesubmissionservice.entity.SubmissionStatus
import ru.abushaev.codesubmissionservice.repository.CodeSubmissionRepository
import ru.abushaev.codesubmissionservice.service.CodeExecutionServiceClient
import ru.abushaev.codesubmissionservice.service.CodeSubmissionService
import java.time.LocalDateTime
import java.util.UUID

@Service
class CodeSubmissionServiceImpl (
    private val codeExecutionServiceClient: CodeExecutionServiceClient,
    private val codeSubmissionRepository: CodeSubmissionRepository
): CodeSubmissionService {
    override fun submitCode(request: CodeSubmissionRequest): Mono<CodeSubmission> {

        val codeSubmission = CodeSubmission(
            code = request.code,
            status = SubmissionStatus.PENDING
        )


        return codeSubmissionRepository.save(codeSubmission)
            .flatMap { savedSubmission ->
                codeExecutionServiceClient.executeCode(savedSubmission.code)
                    .flatMap { response ->
                        val updateSubmission = savedSubmission.copy(
                            status = if (response.error == null) SubmissionStatus.SUCCESS else SubmissionStatus.FAILURE,
                            output = response.output,
                            error = response.error,
                            updateAt = LocalDateTime.now()
                        )
                        codeSubmissionRepository.save(updateSubmission)
                    }
                    .onErrorResume { e ->
                        val failedSubmission = savedSubmission.copy(
                            status = SubmissionStatus.FAILURE,
                            error = e.message,
                            updateAt = LocalDateTime.now()
                        )
                        codeSubmissionRepository.save(failedSubmission)
                    }
            }
    }
}