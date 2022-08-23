package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void toStringTest() throws DukeException {
        String expected = "[E][ ] project meeting (at: 12 Dec 2022 11:00 AM)";
        Task task = new Event("project meeting", "12/12/2022 1100", false);
        String actual = task.toString();
        assertEquals(expected, actual);
    }
}
