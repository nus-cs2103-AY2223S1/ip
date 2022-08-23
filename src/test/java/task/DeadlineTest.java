package task;

import jduke.task.Deadline;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeadlineTest {
    @Test
    public void deadlineDateTest() {
        Deadline deadline = new Deadline("Deadline", "23/8/2022");
        assertEquals("[D][ ] Deadline (by: Aug 23 2022)", deadline.toString());
        assertEquals("D | 0 | Deadline | 23/8/2022", deadline.toStorageFormat());
    }

    @Test
    public void deadlineDateTimeTest() {
        Deadline deadline = new Deadline("Deadline", "16/11/2000 2359");
        assertEquals("[D][ ] Deadline (by: Nov 16 2000 11:59 PM)", deadline.toString());
        assertEquals("D | 0 | Deadline | 16/11/2000 2359", deadline.toStorageFormat());
    }


    @Test
    public void markDeadlineTest() {
        Deadline deadline = new Deadline("Mark Deadline", "20/10/2012 1159");
        deadline.markAsDone();
        assertEquals("[D][X] Mark Deadline (by: Oct 20 2012 11:59 AM)", deadline.toString());
        assertEquals("D | 1 | Mark Deadline | 20/10/2012 1159", deadline.toStorageFormat());
    }

    @Test
    public void unmarkDeadlineTest() {
        Deadline deadline = new Deadline("Unmark Deadline", "20/10/2012 1159");
        deadline.markAsUndone();
        assertEquals("[D][ ] Unmark Deadline (by: Oct 20 2012 11:59 AM)", deadline.toString());
        assertEquals("D | 0 | Unmark Deadline | 20/10/2012 1159", deadline.toStorageFormat());
    }

    @Test
    public void equalDateDeadlineTest() {
        Deadline deadline = new Deadline("Unmark Deadline", "20/10/2012 1159");
        deadline.markAsUndone();
        assertTrue(deadline.isEqualDate(LocalDate.parse("2012-10-20")));
        assertFalse(deadline.isEqualDate(LocalDate.parse("2012-10-21")));
    }
}
