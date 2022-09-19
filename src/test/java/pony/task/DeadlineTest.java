package pony.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for Deadline task.
 */
class DeadlineTest {

    /**
     * Test if a deadline task is correctly created.
     */
    @Test
    public void createTaskTest() {
        Deadline task = new Deadline("Math Homework", "2022-07-29 16:00");
        assertEquals("[D][ ] Math Homework (by: Jul 29 2022 16:00)", task.toString());
    }

}
