package duke.utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeExceptionTest {
    private DukeException dukeException = new DukeException("This is a test DukeException!");

    @Test
    public void dukeException_message_correct() {
        assertEquals("This is a test DukeException!", dukeException.getMessage());
    }
}
