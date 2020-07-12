package me.jiashenb.trysam;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;

import java.time.Instant;

public class S3Putter implements RequestHandler<String, Void> {

    private final S3Client client;

    public S3Putter(S3Client client) {
        this.client = client;
    }

    public S3Putter() {
        this.client = S3Client.builder().build();
    }

    @Override
    public Void handleRequest(String s, Context context) {
        String bucket = "jiashenb-" + getEpochString();
        client.createBucket(CreateBucketRequest
                .builder()
                .bucket(bucket)
                .build());
        System.out.println("Created bucket " + bucket);
        System.out.println("Hello in S3Putter v2!");

        return null;
    }

    private String getEpochString() {
        return Long.valueOf(Instant.now().getEpochSecond()).toString();
    }
}
