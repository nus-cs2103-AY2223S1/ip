package duke.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import duke.exceptions.DukeDateTimeParseException;
import duke.exceptions.DukeIncorrectCommandParamsException;
import duke.exceptions.DukeInsufficientCommandParamsException;
import duke.exceptions.DukeUnknownCommandException;
import org.junit.jupiter.api.Test;

import duke.commands.ByeCommand;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.TodoCommand;
import duke.commands.UnmarkCommand;
import duke.exceptions.DukeException;

class ParserTest {

    @Test
    void parseCommand_correctInputs_returnCorrectly() {
        try {
            assertEquals(new ByeCommand(), Parser.parseCommand("bye"));
            assertEquals(new ListCommand(), Parser.parseCommand("list"));
            assertEquals(new MarkCommand(2), Parser.parseCommand("mark 3"));
            assertEquals(new UnmarkCommand(10), Parser.parseCommand("unmark 11"));
            assertEquals(new DeleteCommand(7), Parser.parseCommand("delete 8"));
            assertEquals(new TodoCommand("finish OOP"), Parser.parseCommand("todo finish OOP"));
            assertEquals(new DeadlineCommand("finish test cases", LocalDateTime.of(2022, 8, 25, 23, 59)),
                    Parser.parseCommand("deadline finish test cases /by 22-8-25 2359"));
            assertEquals(new EventCommand("finish iP", LocalDateTime.of(2022, 9, 25, 02, 59)),
                    Parser.parseCommand("event finish iP /at 22-9-25 0259"));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void parseCommand_acceptableInputs_returnCorrectly() {
        try {
            assertEquals(new ByeCommand(), Parser.parseCommand("bye "));
            assertEquals(new ListCommand(), Parser.parseCommand("list   "));
            assertEquals(new EventCommand("finish iP", LocalDateTime.of(2022, 9, 25, 02, 59)),
                    Parser.parseCommand("event finish iP /at   22-9-25 0259  "));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void parseCommand_incorrectInputs_exceptionThrown() {
        try {
            Parser.parseCommand("bb");
            fail();
        } catch (DukeException e) {
            assertEquals(new DukeUnknownCommandException().getMessage(), e.getMessage());
        }

        try {
            Parser.parseCommand("mark sp");
            fail();
        } catch (DukeException e) {
            assertEquals(new DukeIncorrectCommandParamsException().getMessage(), e.getMessage());
        }

        try {
            Parser.parseCommand("unmark ");
            fail();
        } catch (DukeException e) {
            assertEquals(new DukeIncorrectCommandParamsException().getMessage(), e.getMessage());
        }

        try {
            Parser.parseCommand("deadline exception thrown /at 22-08-25 2525");
            fail();
        } catch (DukeException e) {
            assertEquals(new DukeInsufficientCommandParamsException().getMessage(), e.getMessage());
        }

        try {
            Parser.parseCommand("deadline exception thrown /by 090909");
            fail();
        } catch (DukeException e) {
            assertEquals(new DukeDateTimeParseException().getMessage(), e.getMessage());
        }

        try {
            Parser.parseCommand("deadline exception thrown /by 22-08-25 2525");
            fail();
        } catch (DukeException e) {
            assertEquals(new DukeDateTimeParseException().getMessage(), e.getMessage());
        }

        try {
            Parser.parseCommand("deadline exception thrown /by 22-08-25");
            fail();
        } catch (DukeException e) {
            assertEquals(new DukeDateTimeParseException().getMessage(), e.getMessage());
        }
    }

    @Test
    void parseDateTime() {
        LocalDateTime dateTime = null;
        try {
            dateTime = Parser.parseDateTime("2022-11-03 2359");
        } catch (DukeException e) {
            fail();
        }
        assertEquals(LocalDateTime.of(2022, 11, 3, 23, 59) , dateTime);

        LocalDateTime dateTime2 = null;
        try {
            dateTime2 = Parser.parseDateTime("22-11-03 0159");
        } catch (DukeException e) {
            fail();
        }
        assertEquals(LocalDateTime.of(2022, 11, 3, 1, 59) , dateTime2);
    }
}
