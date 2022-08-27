package duke.task;

import org.junit.jupiter.api.Test;
import task.Event;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void addEventTest() {
        Event event = new Event("Event Test 1", LocalDate.parse("2022-01-01"));
        assertEquals("[E][ ] Event Test 1(at: 01 Jan 2022)", event.toString());
    }

    @Test
    public void saveEventTest() {
        Event event = new Event("Event Test 1", LocalDate.parse("2022-01-21"));
        assertEquals("E##N##Event Test 1##2022-01-21", event.stringify());
    }
}
