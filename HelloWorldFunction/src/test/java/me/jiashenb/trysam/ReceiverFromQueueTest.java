package me.jiashenb.trysam;

import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import org.junit.jupiter.api.Test;

import java.util.Collections;

class ReceiverFromQueueTest {

    @Test
    public void shouldNotThrow() {
        SQSEvent event = new SQSEvent();
        event.setRecords(Collections.EMPTY_LIST);

        new ReceiverFromQueue().handleRequest(event, null);
    }
}