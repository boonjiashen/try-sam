package me.jiashenb.trysam;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class DdbPutter implements RequestHandler<String, Void> {

    private static final String TABLE_NAME_ENVVAR = "TABLE_NAME";

    private static final String TABLE_NAME = System.getenv(TABLE_NAME_ENVVAR);

    @Override
    public Void handleRequest(String s, Context context) {
        System.out.println(TABLE_NAME);

        return null;
    }
}
