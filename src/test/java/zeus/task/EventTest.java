package zeus.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testDateString() {
        Event e = new Event("birthday", "22 Aug 2022");
        assertEquals("[E][ ] birthday (at: 22 Aug 2022)", e.toString(), "toString() method works");

        e.markAsDone();
        assertEquals("[E][X] birthday (at: 22 Aug 2022)", e.toString(), "markAsDone() method works");
    }

    @Test
    void testLocalDateTime() {
        LocalDateTime ld = LocalDateTime.parse("2022-08-22 1600");
        Event e = new Event("birthday", ld);
        assertEquals("[E][ ] birthday (at: Aug 22 2022 1600)", e.toString(), "toString() method works");

        e.markAsDone();
        assertEquals("[E][X] birthday (at: Aug 22 2022 1600)", e.toString(), "markAsDone() method works");
    }
}
