package wanya;

import wanya.task.Event;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void eventToStringTest() {
        Event event = new Event("Homework", "2022-09-12 12:00");
        assertEquals("[E][ ] Homework(at: Sep 12 2022 12:00 PM)", event.toString());

        Event event2 = new Event("Homework2", true, "2022-08-30 13:13");
        assertEquals("[E][X] Homework2(at: Aug 30 2022 01:13 PM)", event2.toString());
    }

    @Test
    public void eventToStorageTest() {
        Event event = new Event("Homework", "2022-09-12 12:00");
        assertEquals("E|0|Homework|2022-09-12 12:00", event.toStorageString());

        Event event2 = new Event("Homework2", true, "2022-08-30 13:13");
        assertEquals("E|1|Homework2|2022-08-30 13:13", event2.toStorageString());
    }
}
