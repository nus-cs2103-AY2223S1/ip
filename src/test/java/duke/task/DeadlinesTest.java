package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import duke.DukeException;

public class DeadlinesTest {
    @Test
    @DisplayName("Check String Output of Deadline Object")
    public void checkStringOutputOfDeadlineObject() {
        String output = null;
        try {
            output = new Deadlines("read book", "10/10/2000 1000", false).toString();
        } catch (DukeException e) {
            fail(e.getMessage());
        }
        assertEquals("[D][ ] read book (by: Oct 10 2000 1000)", output);
    }

    @Test
    @DisplayName("Check Save String Output of Deadline Object")
    public void checkSaveStringOutputOfDeadlineObject() {
        String output = null;
        try {
            output = new Deadlines("read book", "10/10/2000 1000", false).toSaveString();
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }
        assertEquals("deadline 0 read book 10/10/2000 1000", output);
    }
}
