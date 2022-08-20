package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void toDo_toString_testDescription() {
        assertEquals("[T][ ] borrow book", new ToDo("borrow book").toString());
    }

    @Test
    public void toDo_getSaveString_testDescription() {
        assertEquals("T | 0 | borrow book", new ToDo("borrow book").getSaveString());
    }
}
