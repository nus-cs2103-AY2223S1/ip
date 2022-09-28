package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

class ExitCommandTest extends CommandTest {

    private ExitCommand exitCommand;

    @BeforeEach
    public void declareExitCommand() {
        exitCommand = new ExitCommand();
    }

    @Test
    void isExit_alwaysTrue_success() {
        assertTrue(exitCommand.isExit());
    }

    @Test
    void execute_message_success() throws DukeException {
        assertEquals(testCommandExecution(exitCommand),
                "Bye. Hope to see you again soon!");
    }
}
