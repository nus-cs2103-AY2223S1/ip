package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void toString_dummyDeadline_success() {
        Deadline deadline = new Deadline("test", "2022-08-24");
        assertEquals("[D][ ] test (by: 24 Aug 2022)", deadline.toString());
    }

    @Test
    public void getType_dummyDeadline_success() {
        Deadline deadline = new Deadline("test", "2022-08-24");
        assertEquals("D", deadline.getType());
    }

    @Test
    public void getDate_dummyDeadline_success() {
        Deadline deadline = new Deadline("test", "2022-08-24");
        assertEquals("2022-08-24", deadline.getDate());
    }

    @Test
    public void isOnDate_sameDate_success() {
        Deadline deadline = new Deadline("test", "2022-08-24");
        assertTrue(deadline.isOnDate("2022-08-24"));
    }

    @Test
    public void isOnDate_differentDate_success() {
        Deadline deadline = new Deadline("test", "2022-08-24");
        assertFalse(deadline.isOnDate("2022-01-02"));
    }
}
