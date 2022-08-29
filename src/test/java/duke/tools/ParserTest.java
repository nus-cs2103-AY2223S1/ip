package duke.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

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
            assertEquals("Exception: Unknown command.", e.getMessage());
        }

        try {
            Parser.parseCommand("mark sp");
            fail();
        } catch (DukeException e) {
            assertEquals("Exception: Incorrect command parameters.", e.getMessage());
        }

        try {
            Parser.parseCommand("unmark ");
            fail();
        } catch (DukeException e) {
            assertEquals("Exception: Incorrect command parameters.", e.getMessage());
        }

        try {
            Parser.parseCommand("deadline exception thrown /at 22-08-25 2525");
            fail();
        } catch (DukeException e) {
            assertEquals("Exception: Insufficient command parameters.", e.getMessage());
        }

        try {
            Parser.parseCommand("deadline exception thrown /by 090909");
            fail();
        } catch (DukeException e) {
            assertEquals("Exception: Cannot parse datetime.", e.getMessage());
        }

        try {
            Parser.parseCommand("deadline exception thrown /by 22-08-25 2525");
            fail();
        } catch (DukeException e) {
            assertEquals("Exception: Cannot parse datetime.", e.getMessage());
        }

        try {
            Parser.parseCommand("deadline exception thrown /by 22-08-25");
            fail();
        } catch (DukeException e) {
            assertEquals("Exception: Cannot parse datetime.", e.getMessage());
        }
    }

    @Test
    void parseDateTime() {
    }
}
