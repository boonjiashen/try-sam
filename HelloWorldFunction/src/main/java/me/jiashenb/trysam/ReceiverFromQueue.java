package me.jiashenb.trysam;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;

public class ReceiverFromQueue implements RequestHandler<SQSEvent, Void> {

    @Override
    public Void handleRequest(SQSEvent event, Context context) {
        event.getRecords()
                .stream()
                .forEach(message -> System.out.println(message.getBody()));

        return null;
    }
}
