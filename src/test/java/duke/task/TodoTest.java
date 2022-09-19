package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class TodoTest {

    @Test
    public void toStringTest() throws DukeException {
        String expected = "[T][ ] homework";
        Task task = new Todo("homework");
        String actual = task.toString();
        assertEquals(expected, actual);
    }
}
