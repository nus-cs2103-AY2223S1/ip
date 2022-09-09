import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void addTodoTest() {
        Todo newTodo = new Todo("swim");
        assertEquals(newTodo.toString(), "[T][] swim");
    }

    @Test
    public void markTodoTest() {
        Todo newTodo = new Todo("play basketball");
        newTodo.mark();
        assertEquals("[T][X] play basketball", newTodo.toString());
    }
}
