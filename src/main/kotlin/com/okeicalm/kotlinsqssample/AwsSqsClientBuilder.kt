package com.okeicalm.kotlinsqssample

import aws.sdk.kotlin.runtime.endpoint.AwsEndpoint
import aws.sdk.kotlin.runtime.endpoint.AwsEndpointResolver
import aws.sdk.kotlin.services.sqs.SqsClient
import aws.smithy.kotlin.runtime.http.endpoints.Endpoint
import org.springframework.stereotype.Component

@Component
class AwsSqsClientBuilder {
    fun build(): SqsClient {
        return SqsClient {
            region = "ap-northeast-1"
            endpointResolver =
                AwsEndpointResolver { _, _ ->
                    AwsEndpoint(endpoint = Endpoint("http://localstack:4566"))
                }
        }
    }
}