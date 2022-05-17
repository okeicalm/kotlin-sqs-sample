package com.okeicalm.kotlinsqssample.controller

import aws.sdk.kotlin.services.sqs.model.ReceiveMessageRequest
import aws.sdk.kotlin.services.sqs.model.SendMessageRequest
import com.okeicalm.kotlinsqssample.AwsSqsClientBuilder
import kotlinx.coroutines.runBlocking
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

const val sampleQueueUrl = "http://localhost:4566/000000000000/sample-queue.fifo"

@RestController
class MessageController(private val builder: AwsSqsClientBuilder) {
    @PostMapping("/messages/produce")
    fun create(@RequestParam message: String): ResponseEntity<String> {
        runBlocking {
            val sendRequest = SendMessageRequest {
                queueUrl = sampleQueueUrl
                messageBody = message
                messageGroupId = UUID.randomUUID().toString()
            }
            val client = builder.build()
            client.sendMessage(sendRequest)
        }

        return ResponseEntity.ok().build()
    }

    @GetMapping("/messages/consume")
    fun index(): String {
        var messages: String

        runBlocking {
            val receiveMessageRequest = ReceiveMessageRequest {
                queueUrl = sampleQueueUrl
                maxNumberOfMessages = 5
            }

            val client = builder.build()
            val response = client.receiveMessage(receiveMessageRequest)
            messages = response.messages?.map { it.body }?.joinToString().toString()
        }

        return messages
    }
}
