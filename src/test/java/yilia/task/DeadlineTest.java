package yilia.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private final Deadline deadline1 = new Deadline("climb the mountain", "2021-01-23");
    private final Deadline deadline2 = new Deadline("hand in the assignment", true, "2022-05-22");

    @Test
    public void toString_normalInput_writtenCorrectly() {
        assertEquals(deadline1.toString(), "[D][ ] climb the mountain (by: Jan 23 2021)");
        assertEquals(deadline2.toString(), "[D][X] hand in the assignment (by: May 22 2022)");
    }

    @Test
    public void status_check_statusCorrect() {
        deadline1.setDone();
        deadline2.setDone();
        assertTrue(deadline1.status());
        assertTrue(deadline2.status());
    }

    @Test
    public void status_uncheck_statusCorrect() {
        deadline1.setNotDone();
        deadline2.setNotDone();
        assertFalse(deadline1.status());
        assertFalse(deadline2.status());
    }

    @Test
    public void parse_normalInput_writtenCorrectly() {
        assertEquals(deadline1.parse(), "D / 0 / climb the mountain / 2021-01-23");
        assertEquals(deadline2.parse(), "D / 1 / hand in the assignment / 2022-05-22");
    }
}
