package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    /**
     * Tests save format conversion method in ToDo class with 2 valid inputs.
     * Test should return successfully if the converted string matches the expected output.
     */
    @Test
    public void saveFormat_saveFormatConversion_success() {
        ToDo t0 = new ToDo("task0", Priority.NONE);
        ToDo t1 = new ToDo("task1", Priority.LOW);
        ToDo t2 = new ToDo("task2", Priority.MEDIUM);
        ToDo t3 = new ToDo("task3", Priority.HIGH);
        t1.markDone();
        assertEquals("T | none | 0 | task0", t0.saveFormat());
        assertEquals("T | low | 1 | task1", t1.saveFormat());
        assertEquals("T | medium | 0 | task2", t2.saveFormat());
        assertEquals("T | high | 0 | task3", t3.saveFormat());
    }
}
