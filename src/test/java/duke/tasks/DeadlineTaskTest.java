package duke.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTaskTest {
    @Test
    public void ToStringTest() {
        Task deadlineTask = new DeadlineTask("read book", LocalDate.parse("2019-08-22"));
        assertEquals("[D][ ] read book (by: Aug 22 2019)", deadlineTask.toString());
    }

    @Test
    public void ToSaveStringTest() {
        Task deadlineTask = new DeadlineTask("read book", LocalDate.parse("2019-08-22"));
        assertEquals("D | 0 | read book | 2019-08-22", deadlineTask.toSaveString());
    }
}
