package duke.task;

import duke.util.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void matchKeyword_validKeywords_success() throws DukeException {
        assertEquals(true, new Task("walk the dog in the park", false, "").matchKeyword("walk"));
        assertEquals(true, new Task("walk the dog in the park", false, "").matchKeyword("dog"));
        assertEquals(true, new Task("walk the dog in the park", false, "").matchKeyword("park"));
    }

    @Test
    public void matchKeyword_invalidKeywords_failure() throws DukeException {
        assertEquals(false, new Task("walk the dog in the park", false, "").matchKeyword("walking"));
        assertEquals(false, new Task("walk the dog in the park", false, "").matchKeyword("cat"));
        assertEquals(false, new Task("walk the dog in the park", false, "").matchKeyword("mall"));
        assertEquals(false, new Task("walk the dog in the park", false, "").matchKeyword("outside"));
    }
}
