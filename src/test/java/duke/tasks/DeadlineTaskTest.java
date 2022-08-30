package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTaskTest {
    @Test
    public void toStringTest() {
        Task deadlineTask = new DeadlineTask("read book", LocalDate.parse("2019-08-22"));
        assertEquals("[D][ ] read book (by: Aug 22 2019)", deadlineTask.toString());
    }

    @Test
    public void toSaveStringTest() {
        Task deadlineTask = new DeadlineTask("read book", LocalDate.parse("2019-08-22"));
        assertEquals("D | 0 | read book | 2019-08-22", deadlineTask.toSaveString());
    }
}
