import aws.sdk.kotlin.services.sqs.SqsClient
import aws.sdk.kotlin.services.sqs.model.CreateQueueRequest
import aws.sdk.kotlin.services.sqs.model.GetQueueUrlRequest
import kotlinx.coroutines.runBlocking

//fun main(args: Array<String>) {
//    println("hog" +
//            "e" +
//            "e")
//    println(args.joinToString())
//}

fun main(args: Array<String>) = runBlocking {
    println(args.joinToString())
    val createQueueRequest = CreateQueueRequest {
        queueName = "example1"
    }

    val client = SqsClient { region = "ap-northeast-1" }
    client.createQueue(createQueueRequest)
    val getQueueRequest = GetQueueUrlRequest {
        queueName = "example1"
    }

    val getQueueUrlResponse = client.getQueueUrl(getQueueRequest)

    println(getQueueUrlResponse.queueUrl.toString())
}
