package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Class to test TaskList Class.
 */
public class TaskListTest {
    /**
     * Test to test getTask method.
     */
    @Test
    public void getTaskTest() {
        TaskList list = new TaskList();
        try {
            list.get(-1);
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index -1 out of bounds for length 0", e.getMessage());
        }

    }

}
