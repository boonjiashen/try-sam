package me.jiashenb.trysam;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class S3Putter implements RequestHandler<String, Void> {
    @Override
    public Void handleRequest(String s, Context context) {
        System.out.println("Hello in S3Putter!");

        return null;
    }
}
