package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class MarkCommandTest {
    private final MarkCommand markTask = new MarkCommand(3);

    public MarkCommandTest() throws DukeException {
    }

    @Test
    public void markCommandTest() {
        assertEquals(3, markTask.getTaskToMark());
    }
}
