package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    void eventTest() {
        Event test = new Event("event", "eventTime");
        assertEquals("[E][ ] event (at: eventTime)", test.toString(), "toString() method works");

        test.markAsDone();
        assertEquals("[E][X] event (at: eventTime)", test.toString(), "markAsDone() method works");
    }
}
