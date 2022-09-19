package pony.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for Event task.
 */
public class EventTest {

    /**
     * Test if an event task is correctly created.
     */
    @Test
    public void createTaskTest() {
        Event task = new Event("Math Homework", "2022-07-29 16:00");
        assertEquals("[E][ ] Math Homework (at: Jul 29 2022 16:00)", task.toString());
    }
    
}
