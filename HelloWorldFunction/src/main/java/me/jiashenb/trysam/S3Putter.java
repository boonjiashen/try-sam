package me.jiashenb.trysam;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

public class S3Putter implements RequestHandler<String, Void> {

    private final DynamoDbClient client;

    public S3Putter(DynamoDbClient client) {
        this.client = client;
    }

    public S3Putter() {
        this.client = DynamoDbClient.builder().build();
    }

    @Override
    public Void handleRequest(String s, Context context) {
        client.listTables().tableNames().forEach(name -> System.out.println(name));
        System.out.println("Hello in S3Putter v2!");

        return null;
    }
}
