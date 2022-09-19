import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.Parser;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.HighCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;

public class ParserTest {
    @Test
    public void parse_validBye_success() {
        Command c = Parser.parse("bye");
        assertEquals(c, new ByeCommand());
    }

    @Test
    public void parse_invalidBye_exceptionThrown() {
        try {
            Parser.parse("bye blah");
        } catch (Exception e) {
            assertEquals("Please re-enter the command only.", e.getMessage());
        }
    }

    @Test
    public void parse_validList_success() {
        Command c = Parser.parse("list");
        assertEquals(c, new ListCommand());
    }

    @Test
    public void parse_invalidList_exceptionThrown() {
        try {
            Parser.parse("list blah");
        } catch (Exception e) {
            assertEquals("Please re-enter the command only.", e.getMessage());
        }
    }

    @Test
    public void parse_validMark_success() {
        Command c = Parser.parse("mark 1");
        assertEquals(c, new MarkCommand(0));
    }

    @Test
    public void parse_emptyIndexMark_exceptionThrown() {
        try {
            Parser.parse("mark");
        } catch (Exception e) {
            assertEquals("The index to mark cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parse_invalidIndexMark_exceptionThrown() {
        try {
            Parser.parse("mark blah");
        } catch (Exception e) {
            assertEquals("Please provide a valid index within the list.", e.getMessage());
        }
    }

    @Test
    public void parse_validUnmark_success() {
        Command c = Parser.parse("unmark 1");
        assertEquals(c, new UnmarkCommand(0));
    }

    @Test
    public void parse_emptyIndexUnmark_exceptionThrown() {
        try {
            Parser.parse("unmark");
        } catch (Exception e) {
            assertEquals("The index to unmark cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parse_invalidIndexUnmark_exceptionThrown() {
        try {
            Parser.parse("unmark blah");
        } catch (Exception e) {
            assertEquals("Please provide a valid index within the list.", e.getMessage());
        }
    }

    @Test
    public void parse_validHigh_success() {
        Command c = Parser.parse("high 1");
        assertEquals(c, new HighCommand(0));
    }

    @Test
    public void parse_emptyIndexHigh_exceptionThrown() {
        try {
            Parser.parse("high");
        } catch (Exception e) {
            assertEquals("The index to set to high priority cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parse_invalidIndexHigh_exceptionThrown() {
        try {
            Parser.parse("high blah");
        } catch (Exception e) {
            assertEquals("Please provide a valid index within the list.", e.getMessage());
        }
    }

    @Test
    public void parse_validTodo_success() {
        Command c = Parser.parse("todo Tutorial 1");
        assertEquals(c, new TodoCommand("Tutorial 1"));
    }

    @Test
    public void parse_emptyDescriptionTodo_exceptionThrown() {
        try {
            Parser.parse("todo");
        } catch (Exception e) {
            assertEquals("The description of a todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parse_validDeadline_success() {
        Command c = Parser.parse("deadline Tutorial 1 /by 2022-08-25");
        assertEquals(c, new DeadlineCommand("Tutorial 1", LocalDate.parse("2022-08-25")));
    }

    @Test
    public void parse_emptyDescriptionDeadline_exceptionThrown() {
        try {
            Parser.parse("deadline");
        } catch (Exception e) {
            assertEquals("The description of a deadline cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parse_emptyTimeDeadline_exceptionThrown() {
        try {
            Parser.parse("deadline Tutorial 1");
        } catch (Exception e) {
            assertEquals("Please provide both a description and a time.", e.getMessage());
        }
    }

    @Test
    public void parse_invalidDateDeadline_exceptionThrown() {
        try {
            Parser.parse("deadline Tutorial 1 /by 250822");
        } catch (Exception e) {
            assertEquals("Please provide a date in the format yyyy-mm-dd.", e.getMessage());
        }
    }

    @Test
    public void parse_validEvent_success() {
        Command c = Parser.parse("event Tutorial 1 /at 2022-08-25");
        assertEquals(c, new EventCommand("Tutorial 1", LocalDate.parse("2022-08-25")));
    }

    @Test
    public void parse_emptyDescriptionEvent_exceptionThrown() {
        try {
            Parser.parse("event");
        } catch (Exception e) {
            assertEquals("The description of an event cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parse_emptyTimeEvent_exceptionThrown() {
        try {
            Parser.parse("event Tutorial 1");
        } catch (Exception e) {
            assertEquals("Please provide both a description and a time.", e.getMessage());
        }
    }

    @Test
    public void parse_invalidDateEvent_exceptionThrown() {
        try {
            Parser.parse("event Tutorial 1 /at 250822");
        } catch (Exception e) {
            assertEquals("Please provide a date in the format yyyy-mm-dd.", e.getMessage());
        }
    }

    @Test
    public void parse_validDelete_success() {
        Command c = Parser.parse("delete 1");
        assertEquals(c, new DeleteCommand(0));
    }

    @Test
    public void parse_emptyIndexDelete_exceptionThrown() {
        try {
            Parser.parse("delete");
        } catch (Exception e) {
            assertEquals("The index to delete cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parse_invalidIndexDelete_exceptionThrown() {
        try {
            Parser.parse("delete blah");
        } catch (Exception e) {
            assertEquals("Please provide a valid index within the list.", e.getMessage());
        }
    }

    @Test
    public void parse_validFind_success() {
        Command c = Parser.parse("find tutorial");
        String[] arr = {"tutorial"};
        assertEquals(c, new FindCommand(arr));
    }

    @Test
    public void parse_emptyKeywordsFind_exceptionThrown() {
        try {
            Parser.parse("find");
        } catch (Exception e) {
            assertEquals("Please provide at least one keyword to find.", e.getMessage());
        }
    }
}
