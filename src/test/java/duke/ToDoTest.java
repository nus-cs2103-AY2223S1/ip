package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void toStringTest() {
        assertEquals("[T][ ] ToDo", new ToDo("ToDo").toString());
    }

    @Test
    public void descriptionTest() {
        assertEquals("ToDo", new ToDo("ToDo").description);
    }
}
