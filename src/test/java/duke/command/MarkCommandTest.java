package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

class MarkCommandTest extends CommandTest {

    @Test
    public void execute_markUndoneTask_success() throws DukeException {
        MarkCommand markCommand = new MarkCommand(1);
        String result = testCommandExecution(markCommand);
        assertEquals("Nice! I've marked this task as done:\n  [T] [X] do tutorial", result);
        TODO.unmarkDone();
    }

    @Test
    public void execute_markAlreadyDoneTask_exceptionThrown() throws DukeException {
        MarkCommand markCommand = new MarkCommand(1);
        TODO.markDone();
        assertThrows(DukeException.class, () -> testCommandExecution(markCommand));
    }

}
