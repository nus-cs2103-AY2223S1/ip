package duke;

import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit test for todo.
 */
public class TodoTest {

    /**
     * Tests whether todo is formatted correctly.
     */
    @Test
    public void toDo_createTodo_correctStringFormat(){
        Todo todo = new Todo("Run the first test");
        todo.setDone();
        assertEquals("[T][X] Run the first test", todo.toString());
    }

    /**
     * Tests whether todo is of correct type.
     */
    @Test
    public void toDo_createTodo_correctType(){
        Todo todo = new Todo("Run the second test");
        todo.setDone();
        assertEquals("T", todo.getType());
    }
}