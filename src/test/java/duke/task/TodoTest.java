package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Class to test Todo Class.
 */
public class TodoTest {

    /**
     * Test to test toString method.
     */
    @Test
    public void toStringTest() {
        Todo task = new Todo("testing");
        assertEquals("[T][ ] testing", task.toString());
    }

}
