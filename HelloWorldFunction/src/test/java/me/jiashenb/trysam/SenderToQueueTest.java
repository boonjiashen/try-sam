package me.jiashenb.trysam;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SenderToQueueTest {

    @Mock
    private static SqsClient sqsClient;

    @Test
    public void shouldSendMessage() {
        new SenderToQueue(sqsClient).handleRequest("", null);

        verify(sqsClient).sendMessage(any(SendMessageRequest.class));
    }
}