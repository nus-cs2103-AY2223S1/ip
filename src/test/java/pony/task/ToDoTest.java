package pony.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for ToDo task.
 */
public class ToDoTest {

    /**
     * Test if a todo task is correctly created.
     */
    @Test
    public void createTaskTest() {
        ToDo task = new ToDo("Math Homework");
        assertEquals("[T][ ] Math Homework", task.toString());
    }
    
}
