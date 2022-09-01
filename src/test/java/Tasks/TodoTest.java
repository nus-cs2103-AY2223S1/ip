package Tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Todo test class to test todo object methods
 */
public class TodoTest {

    /**
     * Test for string representation of a todo task
     */
    @Test
    public void addToDoTest() {
        Todo todo = new Todo("task 1");
        assertEquals("[T][ ] task 1", todo.toString());
    }

    /**
     * Test for marking a todo task as done
     */
    @Test
    public void addToDoTestMarked() {
        Todo todo = new Todo("Tester3");
        todo.setMarked();
        assertEquals("[T][X] Tester3", todo.toString());
    }
}
