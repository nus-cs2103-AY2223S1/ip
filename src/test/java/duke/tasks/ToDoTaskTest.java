package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTaskTest {
    @Test
    public void ToStringTest() {
        Task todoTask = new ToDoTask("read book");
        assertEquals("[T][ ] read book", todoTask.toString());
    }

    @Test
    public void ToSaveStringTest() {
        Task todoTask = new ToDoTask("read book");
        assertEquals("T | 0 | read book", todoTask.toSaveString());
    }
}
