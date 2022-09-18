import static org.junit.jupiter.api.Assertions.assertEquals;

import KKBot.tasks.ToDo;
import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void toString_dummyTodo_success() {
        ToDo todo = new ToDo("test");
        assertEquals("[T][ ] test", todo.toString());
    }

    @Test
    public void getType_dummyTodo_success() {
        ToDo todo = new ToDo("test");
        assertEquals("T", todo.getType());
    }

    @Test
    public void getDate_dummyTodo_success() {
        ToDo todo = new ToDo("test");
        assertEquals(" ", todo.getDate());
    }
}