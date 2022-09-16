package duke.DeadlineTest;

import duke.models.Deadline;
import duke.models.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    Task deadline = new Deadline("Testing", "2000-09-15");

    @Test
    public void createDeadlineTest() {
        assertEquals("[D][ ] Testing (by: Sep 15 2000)", deadline.toString());
    }

    @Test
    public void markDeadlineTest() {
        deadline.markAsDone();
        assertEquals("[D][X] Testing (by: Sep 15 2000)", deadline.toString());
    }
}
