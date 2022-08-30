package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventsTest {
    @Test
    public void eventsTestToString() {
        Events test = new Events("test", "test");
        assertEquals("[E][ ] test (by: test)", test.toString());
    }

    @Test
    public void eventsTestTextFormat() {
        Events test = new Events("test", "test");
        assertEquals("E|0|test|test", test.textFormat());
    }
}
