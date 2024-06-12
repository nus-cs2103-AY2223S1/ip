package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the task class and associated methods.
 *
 * @author Yuvaraj Kumaresan
 */
public class TaskTest {

    /**
     * First test task.
     */
    Task testTaskOne = new Task("This is a test task");

    /**
     * Second test task.
     */
    Task testTaskTwo = new Task("This is also a test task");

    /**
     * Tests the getStatusIcon method.
     */
    @Test
    public void statusIconTest() {
        assertEquals(" ", testTaskOne.getStatusIcon());
        testTaskTwo.setIsDone(true);
        assertEquals("X", testTaskTwo.getStatusIcon());
    }

    /**
     * Tests the getIsDone and setIsDone method.
     */
    @Test
    public void isDoneTest() {
        assertEquals(false, testTaskOne.getIsDone());
        testTaskTwo.setIsDone(true);
        assertEquals(true, testTaskTwo.getIsDone());
    }

}
