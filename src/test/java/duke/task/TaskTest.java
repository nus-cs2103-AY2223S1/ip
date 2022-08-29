package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import duke.DukeException;

public class TaskTest {
    @Test
    public void invalidDeadline() {
        assertThrows(DukeException.class, () -> Task.createTask("deadline no_deadline"));
    }

    @Test
    public void invalidEvent() {
        assertThrows(DukeException.class, () -> Task.createTask("event no_event_timing"));
    }
}
