package stashy.data.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

/**
 * Test class for Deadline.
 */
public class DeadlineTest {
    @Test
    public void toString_undoneDeadline_success() {
        LocalDateTime byDateTime = LocalDateTime.of(2022, 8, 10, 12, 55);
        Deadline deadline = new Deadline("Buy book", byDateTime, false);
        assertEquals("[D][ ] Buy book (by: Aug 10 2022 12:55)", deadline.toString());
    }

    @Test
    public void toString_doneDeadline_success() {
        LocalDateTime byDateTime = LocalDateTime.of(2022, 8, 10, 12, 55);
        Deadline deadline = new Deadline("Buy book", byDateTime, true);
        assertEquals("[D][X] Buy book (by: Aug 10 2022 12:55)", deadline.toString());
    }
}
