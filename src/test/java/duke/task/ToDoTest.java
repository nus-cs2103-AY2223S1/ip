package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    /**
     * Tests save format conversion method in ToDo class with 2 valid inputs.
     * Test should return successfully if the converted string matches the expected output.
     */
    @Test
    public void saveFormat_saveFormatConversion_success() {
        ToDo t1 = new ToDo("task1");
        ToDo t2 = new ToDo("task2");
        t1.markDone();
        assertEquals("T | 1 | task1", t1.saveFormat());
        assertEquals("T | 0 | task2", t2.saveFormat());
    }
}
