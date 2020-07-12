package me.jiashenb.trysam;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

public class S3Putter implements RequestHandler<String, Void> {
    @Override
    public Void handleRequest(String s, Context context) {
        DynamoDbClient.builder().build().listTables().tableNames().forEach(name -> System.out.println(name));
        System.out.println("Hello in S3Putter!");

        return null;
    }
}
