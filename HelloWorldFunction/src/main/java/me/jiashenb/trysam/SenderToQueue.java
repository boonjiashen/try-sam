package me.jiashenb.trysam;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import java.util.UUID;

public class SenderToQueue implements RequestHandler<String, Void> {

    public static final String QUEUE_URL_ENVVAR = "QUEUE_URL";

    private static final String QUEUE_URL = System.getenv(QUEUE_URL_ENVVAR);

    private final SqsClient sqsClient;

    public SenderToQueue() {
        this.sqsClient = SqsClient.create();
    }

    public SenderToQueue(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    @Override
    public Void handleRequest(String s, Context context) {
        SendMessageRequest request = SendMessageRequest.builder()
                .messageBody(UUID.randomUUID().toString())
                .queueUrl(QUEUE_URL)
                .build();
        sqsClient.sendMessage(request);

        return null;
    }
}
