package me.jiashenb.trysam;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;

import java.nio.charset.StandardCharsets;

public class StreamConsumer implements RequestHandler<KinesisEvent, Void> {

    @Override
    public Void handleRequest(KinesisEvent event, Context context) {
        event.getRecords()
                .stream()
                .forEach(this::processRecord);

        return null;
    }

    private void processRecord(KinesisEvent.KinesisEventRecord record) {
        String data = getDataOf(record);
        System.out.println(data);
    }

    private String getDataOf(KinesisEvent.KinesisEventRecord record) {
        return StandardCharsets.UTF_8.decode(record.getKinesis().getData()).toString();
    }
}
