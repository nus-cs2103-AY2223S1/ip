package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for ToDo class.
 */
public class ToDoTest {

    /**
     * Tests save format conversion method in ToDo class with 2 valid inputs.
     * Test should return successfully if the converted string matches the expected output.
     */
    @Test
    public void convertToSaveFormat_saveFormatConversion_success() {
        ToDo t1 = new ToDo("task1");
        ToDo t2 = new ToDo("task2");
        t1.markAsDone();
        assertEquals("T | 1 | task1", t1.convertToSaveFormat());
        assertEquals("T | 0 | task2", t2.convertToSaveFormat());
    }
}
