package duke.commands;

import duke.exception.DukeException;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarkCommandTest {
    private MarkCommand markTask = new MarkCommand(3);

    public MarkCommandTest() throws DukeException {
    }

    @Test
    public void MarkCommandTest() {
        assertEquals(3, markTask.taskToMark);
    }
}
