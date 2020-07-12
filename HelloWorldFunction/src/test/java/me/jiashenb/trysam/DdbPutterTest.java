package me.jiashenb.trysam;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DdbPutterTest {

    @Mock
    DynamoDbTable table;

    private DdbPutter putter;

    @Test
    public void shouldNotThrow() throws Exception {
        putter = new DdbPutter(table);
        putter.handleRequest("", null);
    }

    @Test
    public void idShouldSet() {
        DdbPutter.Id id = new DdbPutter.Id();
        String expectedId = "hi";
        id.setId(expectedId);

        assertEquals(id.getId(), expectedId);
    }
}