package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    private ToDo todo = new ToDo("borrow book");

    @Test
    public void test_toDo_toString() {
        assertEquals("[T][ ] borrow book", todo.toString());
    }

    @Test
    public void test_toDo_parse() {
        assertEquals("T#0#borrow book", todo.parse());
    }

}
