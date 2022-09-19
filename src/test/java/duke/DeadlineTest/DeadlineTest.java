package duke.DeadlineTest;

import duke.models.Deadline;
import duke.models.Task;
import duke.utils.Interval;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    Task nonRecurringDeadline = new Deadline("Testing", "2000-09-15");
    Task recurringDeadline = new Deadline("Recurring Test", false, "2000-09-16", Interval.Week);

    @Test
    public void nonRecurringDeadline() {
        assertEquals("[D][ ][ ] Testing (by: Sep 15 2000)", nonRecurringDeadline.toString());
    }

    @Test
    public void recurringDeadline() {
        assertEquals("[D][W][ ] Testing (by: Sep 16 2000)", recurringDeadline.toString());
    }

    @Test
    public void markNonRecurringDeadline() {
        nonRecurringDeadline.markAsDone();
        assertEquals("[D][ ][X] Testing (by: Sep 15 2000)", nonRecurringDeadline.toString());
    }

    @Test
    public void markRecurringDeadline() {
        recurringDeadline.markAsDone();
        assertEquals("[D][W][X] Testing (by: Sep 16 2000)", recurringDeadline.toString());
    }
}
