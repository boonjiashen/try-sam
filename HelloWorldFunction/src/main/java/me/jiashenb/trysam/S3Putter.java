package me.jiashenb.trysam;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.core.client.config.SdkAdvancedAsyncClientOption;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class S3Putter implements RequestHandler<String, Void> {

    private static final String BUCKET_NAME_ENVVAR = "BUCKET_NAME";

    private static final String BUCKET_NAME = System.getenv(BUCKET_NAME_ENVVAR);

    private static final int NUM_OBJECTS_TO_PUT = 50;

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(5);

    private final S3AsyncClient s3AsyncClient;

    public S3Putter(S3AsyncClient s3AsyncClient) {
        this.s3AsyncClient = s3AsyncClient;
    }

    public S3Putter() {
        this.s3AsyncClient = S3AsyncClient.builder()
                .asyncConfiguration(b -> b.advancedOption(SdkAdvancedAsyncClientOption.FUTURE_COMPLETION_EXECUTOR
                , EXECUTOR_SERVICE))
                .build();
    }

    @Override
    public Void handleRequest(String s, Context context) {
        String s3Folder = getEpochString();
        List<CompletableFuture<PutObjectResponse>> futures = Stream.generate(this::generateFilename)
                .limit(NUM_OBJECTS_TO_PUT)
                .map(filename -> PutObjectRequest
                        .builder()
                        .bucket(BUCKET_NAME)
                        .key(s3Folder + "/" + filename)
                        .build())
                .map(request ->
                        s3AsyncClient.putObject(request, AsyncRequestBody.empty())
                )
                .collect(Collectors.toList());
        futures.forEach(CompletableFuture::join);

        return null;
    }

    private String generateFilename() {
        return getEpochString() + "-" + UUID.randomUUID().toString();
    }

    private String getEpochString() {
        return Long.valueOf(Instant.now().getEpochSecond()).toString();
    }
}
