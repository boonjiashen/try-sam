package me.jiashenb.trysam;

import org.junit.jupiter.api.Test;

public class S3PutterTest {

    @Test
    public void shouldNotThrow() {
        new S3Putter().handleRequest("", null);
    }
}