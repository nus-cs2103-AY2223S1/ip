
package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    ToDo todo = new ToDo("borrow book");

    @Test
    public void test_toDo_toString() {
        assertEquals("[T][ ] borrow book", todo.toString());
    }

    @Test
    public void test_toDo_parse() {
        assertEquals("T#0#borrow book", todo.parse());
    }



}