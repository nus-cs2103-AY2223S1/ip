package duke;

import org.junit.jupiter.api.Test;
import task.Task;
import task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void formatToSave_marked() {
        Task task = new Todo("test");
        task.markAsDone();
        String expectedStr = "T//1//test";
        assertEquals(expectedStr, task.formatToSave());
    }
    @Test
    public void formatToSave_unmarked() {
        Task task = new Todo("test");
        task.markAsUndone();
        String expectedStr = "T//0//test";
        assertEquals(expectedStr, task.formatToSave());
    }
}
