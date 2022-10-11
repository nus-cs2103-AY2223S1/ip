package isara;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import isara.task.Deadline;
import isara.task.Task;

public class DeadlineTest {
    @Test
    public void testMarkFunctionalityForDeadline() {
        LocalDate date = LocalDate.parse("2022-05-12");
        Task deadline = new Deadline("Homework", date);
        assertEquals(deadline.toString(), "[D][ ] Homework (by: 2022-05-12)");
        deadline.mark();
        assertEquals(deadline.toString(), "[D][X] Homework (by: 2022-05-12)");
    }

    @Test
    public void testUnmarkFunctionalityForDeadline() {
        LocalDate date = LocalDate.parse("2022-05-12");
        Task deadline = new Deadline("Homework", date);
        deadline.mark();
        assertEquals(deadline.toString(), "[D][X] Homework (by: 2022-05-12)");
        deadline.unmark();
        assertEquals(deadline.toString(), "[D][ ] Homework (by: 2022-05-12)");
    }

    @Test
    public void testGetStatusIconForDeadline() {
        LocalDate date = LocalDate.parse("2022-05-12");
        Task deadline = new Deadline("Homework", date);
        deadline.mark();
        assertEquals(deadline.toString(), "[D][X] Homework (by: 2022-05-12)");
        assertEquals(deadline.getStatusIcon(), "X");
        deadline.unmark();
        assertEquals(deadline.toString(), "[D][ ] Homework (by: 2022-05-12)");
        assertEquals(deadline.getStatusIcon(), " ");
    }
}
