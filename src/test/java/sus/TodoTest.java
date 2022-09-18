package sus;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import sus.task.Todo;

public class TodoTest {

    @Test
    public void newTodoTest() {
        Todo todo = new Todo("This is a test description");
        assertEquals(todo.toString(), "[T][ ] This is a test description");
    }

    @Test
    public void markTodoTest() {
        Todo todo = new Todo("This is a test description");
        todo.setDone(true);
        assertEquals(todo.toString(), "[T][X] This is a test description");
    }
}
