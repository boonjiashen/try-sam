package me.jiashenb.trysam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.services.s3.S3Client;

@ExtendWith(MockitoExtension.class)
public class S3PutterTest {

    @Mock
    private static S3Client client;

    private S3Putter putter;

    @BeforeEach
    public void setup() {
        putter = new S3Putter(client);
    }

    @Test
    public void shouldNotThrow() {
        putter.handleRequest("", null);
    }
}