package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toString_undone() throws DukeException {
        Deadline deadline = new Deadline("description", "31/8/2022");
        deadline.markAsUndone();
        assertEquals("[D][ ] description (by: Aug 31 2022)", deadline.toString());
    }

    @Test
    public void toString_done() throws DukeException {
        Deadline deadline = new Deadline("description", "31/8/2022");
        deadline.markAsDone();
        assertEquals("[D][X] description (by: Aug 31 2022)", deadline.toString());
    }
}
