package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventTest {
    @Test
    public void toStringTest() {
        Event event = new Event("Dinner", LocalDate.parse("2022-08-07"), LocalTime.parse("18:00"));
        assertEquals("[E][ ] Dinner (at: Aug 7 2022 06:00 PM)", event.toString());
    }
    @Test
    public void toFileDataTest() {
        Event event = new Event("Dinner", LocalDate.parse("2022-07-07"), LocalTime.parse("18:00"));
        assertEquals("E | 0 | Dinner | 2022-07-07 18:00", event.toFileData());
    }
}
