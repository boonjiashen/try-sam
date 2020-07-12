package me.jiashenb.trysam;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PutterToDdbTest {

    @Mock
    DynamoDbTable table;

    private PutterToDdb putter;

    @Test
    public void shouldNotThrow() throws Exception {
        putter = new PutterToDdb(table);
        putter.handleRequest("", null);
    }

    @Test
    public void idShouldSet() {
        PutterToDdb.Id id = new PutterToDdb.Id();
        String expectedId = "hi";
        id.setId(expectedId);

        assertEquals(id.getId(), expectedId);
    }
}