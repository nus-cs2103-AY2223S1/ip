package components;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
  @Test
  public void testDeadline() {
    assertEquals("[D][ ] return brush (by: Sunday)",
        new Deadline("return brush", "Sunday").toString());
  }

}