package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * ToDoTest class to test the methods within the ToDo class.
 */
public class ToDoTest {

    @Test
    public void ToDoTest() {
        String actualString = new ToDo("Testing").toString();
        String expectedString = "[T][ ] Testing";

        assertEquals(expectedString, actualString);
    }
}
