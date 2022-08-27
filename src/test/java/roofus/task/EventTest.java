package roofus.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void addEventTest() {
        Task eventTask = new Event("Say hello", "2022-08-23");
        assertEquals("[E][ ] Say hello at: 2022-08-23", eventTask.toString());
    }

    @Test
    public void markEventTest() {
        Task eventTask = new Event("Say hello", "2022-08-23");
        eventTask.mark();
        assertEquals("[E][X] Say hello at: 2022-08-23", eventTask.toString());
    }

    @Test
    public void unmarkEventTest() {
        Task eventTask = new Event("Say hello", "2022-08-23");
        eventTask.mark();
        eventTask.unmark();
        assertEquals("[E][ ] Say hello at: 2022-08-23", eventTask.toString());
    }

    @Test
    public void writeEventTest() {
        Task eventTask = new Event("Say hello", "2022-08-23");
        assertEquals("E | 0 | Say hello | 2022-08-23", eventTask.writeString());
        eventTask.mark();
        assertEquals("E | 1 | Say hello | 2022-08-23", eventTask.writeString());
    }
}
