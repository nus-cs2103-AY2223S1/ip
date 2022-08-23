package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void toStringTest() throws DukeException {
        String expected = "[T][ ] homework";
        Task task = new Todo("homework");
        String actual = task.toString();
        assertEquals(expected, actual);
    }
}
