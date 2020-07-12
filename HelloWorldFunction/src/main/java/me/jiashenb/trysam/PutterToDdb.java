package me.jiashenb.trysam;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.UUID;

public class PutterToDdb implements RequestHandler<String, Void> {

    public static final String TABLE_NAME_ENVVAR = "TABLE_NAME";

    private static final String TABLE_NAME = System.getenv(TABLE_NAME_ENVVAR);

    private final DynamoDbTable<Id> table;

    public PutterToDdb() {
        DynamoDbClient ddbClient = DynamoDbClient.builder().build();
        DynamoDbEnhancedClient ddbEnhancedClient = DynamoDbEnhancedClient.builder().dynamoDbClient(ddbClient).build();
        this.table = ddbEnhancedClient.table(TABLE_NAME, TableSchema.fromBean(Id.class));
    }

    public PutterToDdb(DynamoDbTable<Id> table) {
        this.table = table;
    }

    @Override
    public Void handleRequest(String s, Context context) {
        System.out.println(TABLE_NAME);

        Id item = new Id();
        String id = UUID.randomUUID().toString();
        item.setId(id);
        table.putItem(item);

        System.out.println("ID = " + id);

        return null;
    }

    @DynamoDbBean
    public static final class Id {
        private String id;

        @DynamoDbPartitionKey
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
