package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Event;

public class EventTest {
    @Test
    public void newEventTest() {
        String name = "test1";
        String at = "testing";
        Event newEvent = new Event(name, at);
        assertEquals(String.format("[E][ ] %s (at: %s)", name, at), newEvent.toString());
    }

    @Test
    public void newEventTest_markTask() {
        String name = "test2";
        String at = "testing";
        Event newEvent = new Event(name, at);
        newEvent.markComplete();
        assertEquals(String.format("[E][X] %s (at: %s)", name, at), newEvent.toString());
    }
}
