package zeus.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testDateString() {
        Event event = new Event("birthday", "22 Aug 2022");
        assertEquals("[E][ ] birthday (at: 22 Aug 2022)", event.toString(), "toString() method works");

        event.markAsDone();
        assertEquals("[E][X] birthday (at: 22 Aug 2022)", event.toString(), "markAsDone() method works");
    }

    @Test
    void testLocalDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime ld = LocalDateTime.parse("2022-08-22 1600", formatter);
        Event event = new Event("birthday", ld);
        assertEquals("[E][ ] birthday (at: Aug 22 2022 1600)", event.toString(), "toString() method works");

        event.markAsDone();
        assertEquals("[E][X] birthday (at: Aug 22 2022 1600)", event.toString(), "markAsDone() method works");
    }
}
