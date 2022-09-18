package stashy.data.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for ToDo.
 */
public class ToDoTest {
    @Test
    public void toString_undoneToDo_success() {
        ToDo todo = new ToDo("Do homework", false);
        assertEquals("[T][ ] Do homework", todo.toString());
    }

    @Test
    public void toString_doneToDo_success() {
        ToDo todo = new ToDo("Do homework", true);
        assertEquals("[T][X] Do homework", todo.toString());
    }

    @Test
    public void toString_defaultToDo_success() {
        ToDo todo = new ToDo("Do homework");
        assertEquals("[T][ ] Do homework", todo.toString());
    }
}
