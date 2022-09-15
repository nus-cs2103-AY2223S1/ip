package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventsTest {
    @Test
    public void eventsTestToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        Events test = new Events("test", LocalDateTime.parse("01/01/2025 1600", formatter));
        assertEquals("[E][ ] test (by: Jan 1 2025 1600)", test.toString());
    }

    @Test
    public void eventsTestTextFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        Events test = new Events("test", LocalDateTime.parse("01/01/2025 1600", formatter));
        assertEquals("E|0|test|2025-01-01T16:00", test.textFormat());
    }
}
