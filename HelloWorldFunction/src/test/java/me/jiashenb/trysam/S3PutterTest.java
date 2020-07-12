package me.jiashenb.trysam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class S3PutterTest {

    @Mock
    private static DynamoDbClient client;

    private S3Putter putter;

    @BeforeEach
    public void setup() {
        putter = new S3Putter(client);
    }

    @Test
    public void shouldNotThrow() {
        when(client.listTables()).thenReturn(ListTablesResponse.builder().build());

        putter.handleRequest("", null);
    }
}