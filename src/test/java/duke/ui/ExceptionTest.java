package duke.ui;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import duke.task.Deadline;
import duke.task.Todo;

class ExceptionTest {
    @Test
    public void emptyTodo_exceptionThrown() {
        try {
            Todo test1 = new Todo("");
            assertEquals("[T][ ]", test1);
            fail();
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void dateTimeParse_exceptionThrown() {
        try {
            Deadline test2 = new Deadline(" test2", LocalDate.parse("21 June 1000"));
            assertEquals("[D][ ] test2 (by: Jun 21 1000)", test2);
            fail();
        } catch (Exception e) {
            assertEquals("Text '21 June 1000' could not be parsed at index 0", e.getMessage());
        }
    }
}
