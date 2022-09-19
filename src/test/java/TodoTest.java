import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.task.Todo;

public class TodoTest {
    @Test
    public void markTodoTest(){
        Todo todo = new Todo("buy bread");
        todo.markAsDone();
        assertEquals("[T][X]buy bread", todo.toString());
    }

    @Test
    public void unmarkTodoTest(){
        Todo todo = new Todo("buy bread");
        todo.markAsDone();
        todo.markAsUndone();
        assertEquals("[T][ ]buy bread", todo.toString());
    }
}
