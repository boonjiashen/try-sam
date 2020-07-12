package me.jiashenb.trysam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PutterToS3Test {

    @Mock
    private static S3AsyncClient client;

    private PutterToS3 putter;

    @BeforeEach
    public void setup() {
        putter = new PutterToS3(client);
    }

    @Test
    public void shouldNotThrow() {
        when(client.putObject(any(PutObjectRequest.class), any(AsyncRequestBody.class))).thenReturn(CompletableFuture.completedFuture(null));

        putter.handleRequest("", null);
    }
}