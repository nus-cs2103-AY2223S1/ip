package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

class UnmarkCommandTest extends CommandTest {

    @Test
    public void execute_unmarkDoneTask_success() throws DukeException {
        TODO.markDone();
        UnmarkCommand unmarkCommand = new UnmarkCommand(1);
        String result = testCommandExecution(unmarkCommand);
        assertEquals("OK, I've marked this task as not done yet:\n  [T] [ ] do tutorial", result);
    }

    @Test
    public void execute_unmarkNotDoneTask_exceptionThrown() throws DukeException {
        if (TODO.isDone()) {
            TODO.unmarkDone();
        }
        UnmarkCommand unmarkCommand = new UnmarkCommand(1);
        assertThrows(DukeException.class, () -> testCommandExecution(unmarkCommand));
    }
}
