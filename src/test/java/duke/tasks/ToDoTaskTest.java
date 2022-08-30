package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class ToDoTaskTest {
    @Test
    public void toStringTest() {
        Task todoTask = new ToDoTask("read book");
        assertEquals("[T][ ] read book", todoTask.toString());
    }

    @Test
    public void toSaveStringTest() {
        Task todoTask = new ToDoTask("read book");
        assertEquals("T | 0 | read book", todoTask.toSaveString());
    }
}
