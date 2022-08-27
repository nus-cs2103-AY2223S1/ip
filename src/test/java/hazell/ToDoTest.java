package hazell;

import hazell.exceptions.TaskDescriptionEmpty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void test1() throws TaskDescriptionEmpty {
        ToDo todo = ToDo.create("borrow book");
        assertEquals(todo.toString(), "[T][ ] borrow book");
    }
}
