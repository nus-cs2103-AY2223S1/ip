package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void newTodoTest() {
        TodoTask todo = new TodoTask("This is a test description");
        assertEquals(todo.toString(), "[T][ ] This is a test description");
    }

    @Test
    public void markTodoTest() {
        TodoTask todo = new TodoTask("This is a test description");
        todo.mark();
        assertEquals(todo.toString(), "[T][X] This is a test description");
    }
}
