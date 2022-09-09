package functional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for Event.
 * @author Nicholas Patrick
 */
public class EventTest {
    /**
     * Tests whether Event::toString performs as expected
     */
    @Test
    public void toStringCheck() {
        Event event = new Event("sleep",
            LocalDateTime.of(2022, 10, 19, 18, 17, 16),
            LocalDateTime.of(2022, 11, 20, 19, 18, 17));
        assertEquals(event.toString(), "[E][ ] sleep (from 19 Oct 2022 at 18:17:16 to 20 Nov 2022 at 19:18:17)");
    }
}
