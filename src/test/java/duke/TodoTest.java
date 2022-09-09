package duke;

import org.junit.jupiter.api.Test;

import static duke.DukeConstants.KEY_SEPARATOR;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void formatToSave_marked() {
        Task task = new Todo("test");
        task.isDone = true;
        String expectedStr = "T//1//test";
        assertEquals(expectedStr, task.formatToSave());
    }
    @Test
    public void formatToSave_unmarked() {
        Task task = new Todo("test");
        task.isDone = false;
        String expectedStr = "T//0//test";
        assertEquals(expectedStr, task.formatToSave());
    }
}
