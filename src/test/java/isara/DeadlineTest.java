package isara;

import isara.task.Task;
import isara.task.Deadline;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testMarkFunctionalityForDeadline(){
        LocalDate date = LocalDate.parse("2022-05-12");
        Task deadline = new Deadline("Homework", date);
        assertEquals(deadline.toString(), "[D][ ] Homework (by: 2022-05-12)");
        deadline.markAsDone();
        assertEquals(deadline.toString(), "[D][X] Homework (by: 2022-05-12)");
    }

    @Test
    public void testUnmarkFunctionalityForDeadline(){
        LocalDate date = LocalDate.parse("2022-05-12");
        Task deadline = new Deadline("Homework", date);
        deadline.markAsDone();
        assertEquals(deadline.toString(), "[D][X] Homework (by: 2022-05-12)");
        deadline.unmarkAsDone();
        assertEquals(deadline.toString(), "[D][ ] Homework (by: 2022-05-12)");
    }

    @Test
    public void testGetStatusIconForDeadline(){
        LocalDate date = LocalDate.parse("2022-05-12");
        Task deadline = new Deadline("Homework", date);
        deadline.markAsDone();
        assertEquals(deadline.toString(), "[D][X] Homework (by: 2022-05-12)");
        assertEquals(deadline.getStatusIcon(), "X");
        deadline.unmarkAsDone();
        assertEquals(deadline.toString(), "[D][ ] Homework (by: 2022-05-12)");
        assertEquals(deadline.getStatusIcon(), " ");
    }
}
