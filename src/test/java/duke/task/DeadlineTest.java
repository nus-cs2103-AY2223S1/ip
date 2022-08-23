package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toStringTest() throws DukeException {
        String expected = "[D][ ] homework (by: 12 Dec 2022 18:00 PM)";
        Task task = new Deadline("homework", "12/12/2022 1800", false);
        String actual = task.toString();
        assertEquals(expected, actual);
    }
}
