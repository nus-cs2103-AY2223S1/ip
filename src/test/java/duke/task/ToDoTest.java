package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void convertToSaveFormat_saveFormatConversion_success() {
        ToDo t1 = new ToDo("task1");
        ToDo t2 = new ToDo("task2");
        t1.markAsDone();
        assertEquals("T | 1 | task1", t1.convertToSaveFormat());
        assertEquals("T | 0 | task2", t2.convertToSaveFormat());
    }
}
