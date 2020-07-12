package me.jiashenb.trysam;

import org.junit.jupiter.api.Test;

class DdbPutterTest {

    @Test
    public void shouldNotThrow() {
        new DdbPutter().handleRequest(null, null);
    }
}