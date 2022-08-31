package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testMarkAsDone() {
        Deadline d = new Deadline("Deadline", "2022-12-16 18:00");
        d = d.markAsDone();
        assertTrue(d.isDone);
    }

    @Test
    public void testMarkAsUndone() {
        Deadline d = new Deadline("Deadline", "2022-12-16 18:00");
        d = d.markAsUndone();
        assertFalse(d.isDone);
    }

    @Test
    public void testToString() {
        Deadline d = new Deadline("Deadline", "2022-12-16 18:00");
        assertEquals("[D][ ] Deadline (by: 16 Dec 2022 at 06:00 PM)", d.toString());
    }
    
}
