package me.jiashenb.trysam;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.time.Instant;

public class S3Putter implements RequestHandler<String, Void> {

    private static final String BUCKET_NAME_ENVVAR = "BUCKET_NAME";

    private static final String BUCKET_NAME = System.getenv(BUCKET_NAME_ENVVAR);

    private final S3Client client;

    public S3Putter(S3Client client) {
        this.client = client;
    }

    public S3Putter() {
        this.client = S3Client.builder().build();
    }

    @Override
    public Void handleRequest(String s, Context context) {
        String s3Key = getEpochString();
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(s3Key)
                .build();
        client.putObject(request, RequestBody.empty());
        System.out.println("Put object in bucket " + BUCKET_NAME + " and key " + s3Key);
        System.out.println("Hello in S3Putter v2!");

        return null;
    }

    private String getEpochString() {
        return Long.valueOf(Instant.now().getEpochSecond()).toString();
    }
}
