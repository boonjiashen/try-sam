package me.jiashenb.trysam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.http.HttpStatusCode;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

  private static App app;

  @BeforeEach
  public void setup() {
    app = new App();
  }

  @Test
  public void shouldReturn200() {
    int expectedStatusCode = HttpStatusCode.OK;
    int actualStatusCode = app.handleRequest(null, null).getStatusCode();

    assertEquals(expectedStatusCode, actualStatusCode);
  }
}
