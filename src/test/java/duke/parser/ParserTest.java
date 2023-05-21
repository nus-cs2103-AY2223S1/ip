package duke.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.TodoCommand;
import duke.commands.UnmarkCommand;
import duke.exceptions.UnknownCommandException;
import duke.exceptions.WrongDeadlineFormatException;
import duke.exceptions.WrongEventFormatException;


public class ParserTest {
    @Test
    public void parseCommand_exitCommand_parsedCorrectly() {
        parseCommandAndAssert("bye", ExitCommand.class);
    }

    @Test
    public void parseCommand_listCommand_parsedCorrectly() {
        parseCommandAndAssert("list", ListCommand.class);
    }

    @Test
    public void parseCommand_markCommand_parsedCorrectly() {
        parseCommandAndAssert("mark 1", MarkCommand.class);
    }

    @Test
    public void parseCommand_unmarkCommand_parsedCorrectly() {
        parseCommandAndAssert("unmark 1", UnmarkCommand.class);
    }

    @Test
    public void parseCommand_deleteCommand_parsedCorrectly() {
        parseCommandAndAssert("delete 1", DeleteCommand.class);
    }

    @Test
    public void parseCommand_todoCommand_parsedCorrectly() {
        parseCommandAndAssert("todo abc", TodoCommand.class);
    }

    @Test
    public void parseCommand_deadlineCommand_parsedCorrectly() {
        parseCommandAndAssert("deadline abc /by 2100-01-01 0000", DeadlineCommand.class);
    }

    @Test
    public void parseCommand_deadlineCommandMissingDate_errorMessage() {
        parseCommandAndExpectException("deadline abc", WrongDeadlineFormatException.class);
    }

    @Test
    public void parseCommand_eventCommand_parsedCorrectly() {
        parseCommandAndAssert("event abc /at 2100-01-01 2359", EventCommand.class);
    }

    @Test
    public void parseCommand_eventCommandMissingDate_errorMessage() {
        parseCommandAndExpectException("event abc", WrongEventFormatException.class);
    }

    @Test
    public void parseCommand_findCommand_parsedCorrectly() {
        parseCommandAndAssert("find x", FindCommand.class);
    }

    @Test
    public void parseCommand_invalidCommand_errorMessage() {
        parseCommandAndExpectException("invalidcommand", UnknownCommandException.class);
    }

    /**
     * Checks that parseCommand returns the correct class for a particular input.
     *
     * @param userInput     Input to be tested for.
     * @param expectedClass The expected class to be returned.
     * @param <T>           The type of command.
     */
    public <T extends Command> void parseCommandAndAssert(String userInput, Class<T> expectedClass) {
        Parser parser = new Parser();
        assertDoesNotThrow(() -> {
            Command command = parser.parseCommand(userInput);
            // Ensures that command is the correct command.
            assertTrue(command.getClass().isAssignableFrom(expectedClass));
        });
    }

    /**
     * Checks that parseCommand throws an exception for a particular input.
     *
     * @param userInput         Input to be tested for.
     * @param expectedException The expected exception to be returned.
     * @param <T>               The type of command.
     */
    public <T extends Exception> void parseCommandAndExpectException(String userInput, Class<T> expectedException) {
        Parser parser = new Parser();
        // Ensures that exception thrown is the correct.
        assertThrows(expectedException, () -> parser.parseCommand(userInput));
    }
}
