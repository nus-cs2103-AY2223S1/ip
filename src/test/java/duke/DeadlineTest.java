package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.TaskType;

public class DeadlineTest {
    private Deadline initializeDeadline() {
        return new Deadline("lab ",
                LocalDate.of(2022, 10, 12), null, TaskType.DEADLINE);
    }
    @Test
    public void isDateEqual_success() {
        assertFalse(initializeDeadline().isDateEqual(LocalDate.of(2022, 10, 15)));
    }

    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] lab (by: Oct 12 2022)", initializeDeadline().toString());
    }
}
