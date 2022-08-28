package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Class to test Event Class.
 */
public class EventTest {

    /**
     * Test to test toString method.
     */
    @Test
    public void toStringTest() {
        String[] args = {"testing", "today"};
        Event task = new Event(args);
        assertEquals("[E][ ] testing at: today", task.toString());
    }
}
