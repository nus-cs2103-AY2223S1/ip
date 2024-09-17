package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void addEventTest() {
        Event event = new Event("Event Test 1", LocalDate.parse("2022-08-21"));
        assertEquals("[E][ ] Event Test 1 (at: Aug 21 2022)", event.toString());
    }

    @Test
    public void saveEventTest() {
        Event event = new Event("Event Test 1", LocalDate.parse("2022-08-21"));
        assertEquals("E |   | Event Test 1 | 2022-08-21", event.stringify());
    }
}
