package ru.abushaev.codesubmissionservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CodeSubmissionServiceApplication

fun main(args: Array<String>) {
    runApplication<CodeSubmissionServiceApplication>(*args)
}
