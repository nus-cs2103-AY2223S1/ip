package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testNewEvent() {
        Event ev = new Event("convention", "on",
                LocalDateTime.parse("27/08/2022 1200", DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")),
                LocalTime.parse("1700", DateTimeFormatter.ofPattern("HHmm")));
        assertEquals("[E][ ] convention (on: Sat, 27 Aug 2022, 12:00PM to 05:00PM)",
                ev.toString());
    }
}
