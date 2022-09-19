package duke.DeadlineTest;

import duke.exceptions.DukeException;
import duke.models.Deadline;
import duke.models.FormattedDate;
import duke.models.Task;
import duke.utils.Interval;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void nonRecurringDeadline() throws DukeException {
        FormattedDate testDate = new FormattedDate("2000-09-15");
        Task nonRecurringDeadline = new Deadline("Testing", testDate);
        assertEquals("[D][ ][ ] Testing (by: Sep 15 2000)", nonRecurringDeadline.toString());
    }

    @Test
    public void recurringDeadline() throws DukeException {
        FormattedDate testDate = new FormattedDate("2000-09-16");
        Task recurringDeadline = new Deadline("Recurring Test", false, testDate, Interval.Week);
        assertEquals("[D][W][ ] Testing (by: Sep 16 2000)", recurringDeadline.toString());
    }

    @Test
    public void markNonRecurringDeadline() throws DukeException {
        FormattedDate testDate = new FormattedDate("2000-09-15");
        Task nonRecurringDeadline = new Deadline("Testing", testDate);
        nonRecurringDeadline.markAsDone();
        assertEquals("[D][ ][X] Testing (by: Sep 15 2000)", nonRecurringDeadline.toString());
    }

    @Test
    public void markRecurringDeadline() throws DukeException {
        FormattedDate testDate = new FormattedDate("2000-09-16");
        Task recurringDeadline = new Deadline("Recurring Test", false, testDate, Interval.Week);
        recurringDeadline.markAsDone();
        assertEquals("[D][W][X] Testing (by: Sep 16 2000)", recurringDeadline.toString());
    }
}
