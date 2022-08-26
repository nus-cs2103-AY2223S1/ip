package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventsTest {
    @Test
    public void EventsTestToString() {
        Events test = new Events("test", "test");
        assertEquals("[E][ ] test (by: test)", test.toString());
    }

    @Test
    public void EventsTestTextFormat() {
        Events test = new Events("test", "test");
        assertEquals("E|0|test|test", test.textFormat());
    }
}
