package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A class that tests the ToDo class.
 */
public class ToDoTest {

    /**
     * Tests if the toString() method of the ToDo class works as expected.
     */
    @Test
    public void descriptionTest() {
        String description = "assignment";
        ToDo test = new ToDo(description);
        assertEquals("[T][ ]assignment", test.toString());
    }
}
