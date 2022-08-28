import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import task.Task;
import task.Todo;


public class TodoTest {

    @Test
    public void getStatusIcon_unmarked() {
        Task todo = new Todo("read book");
        todo.isMark(false);
        assertEquals(" ", todo.getStatusIcon());
    }

    @Test
    public void getStatusIcon_marked() {
        Task todo = new Todo("read book");
        todo.isMark(true);
        assertEquals("X", todo.getStatusIcon());
    }

    @Test
    public void toString_success() {
        Task todo = new Todo("read book");
        assertEquals("[T][ ] read book", todo.toString());
    }
}
