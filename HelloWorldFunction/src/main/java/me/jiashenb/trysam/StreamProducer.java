package me.jiashenb.trysam;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.kinesis.KinesisClient;
import software.amazon.awssdk.services.kinesis.model.PutRecordRequest;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.serverless.ssmcachingclient.SsmParameterCachingClient;

import java.time.Duration;
import java.util.UUID;

public class StreamProducer implements RequestHandler<Void, Void> {

    private static final String STREAM_NAME_PARAMETER = "MyStreamName";

    private final KinesisClient kinesis;
    private final SsmParameterCachingClient ssm;

    public StreamProducer() {
        this.kinesis = KinesisClient.create();
        this.ssm = new SsmParameterCachingClient(SsmClient.create(), Duration.ofMinutes(10L));
    }

    @Override
    public Void handleRequest(Void aVoid, Context context) {
        String data = generateString();
        System.out.println(String.format("Putting data %s into stream %s", data, getStreamName()));

        kinesis.putRecord(PutRecordRequest.builder()
                .data(SdkBytes.fromByteArray(data.getBytes()))
                .streamName(getStreamName())
                .partitionKey(Integer.toString(data.hashCode()))
                .build());
        return null;
    }

    private String generateString() {
        return UUID.randomUUID().toString();
    }

    private String getStreamName() {
        return this.ssm.getAsString(STREAM_NAME_PARAMETER);
    }
}
