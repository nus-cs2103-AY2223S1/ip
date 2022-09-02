package components;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
  @Test
  public void testDeadline() {
    assertEquals("[E][ ] return brush (at: Sunday)",
        new Event("return brush", "Sunday").toString());
  }

}