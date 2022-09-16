package hazell;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import hazell.entities.ToDo;
import hazell.exceptions.TaskDescriptionEmpty;

public class ToDoTest {
    @Test
    public void test1() throws TaskDescriptionEmpty {
        ToDo todo = ToDo.create("borrow book");
        assertEquals(todo.toString(), "[T][ ] borrow book");
    }
}
