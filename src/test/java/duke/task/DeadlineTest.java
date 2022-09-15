package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void deadlineTestToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        Deadline test = new Deadline("test", LocalDateTime.parse("01/01/2025 1600", formatter));
        assertEquals("[D][ ] test (by: Jan 1 2025 1600)", test.toString());
    }

    @Test
    public void deadlineTestTextFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        Deadline test = new Deadline("test", LocalDateTime.parse("01/01/2025 1600", formatter));
        assertEquals("D|0|test|2025-01-01T16:00", test.textFormat());
    }
}
