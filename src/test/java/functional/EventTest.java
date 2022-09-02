package functional;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EventTest {
  @Test
  public void toStringCheck() {
    Event event = new Event("sleep",
        LocalDateTime.of(2022, 10, 19, 18, 17, 16),
        LocalDateTime.of(2022, 11, 20, 19, 18, 17));
    assertEquals(event.toString(), "[E][ ] sleep (from 19 Oct 2022 at 18:17:16 to 20 Nov 2022 at 19:18:17)");
  }
}
