package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.commands.ByeCommand;
import duke.commands.DateCommand;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.TodoCommand;
import duke.commands.UnmarkCommand;


public class ParserTest {

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        try {
            Parser.parse("bruh");
            fail();
        } catch (DukeException e) {
            assertEquals("I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

    @Test
    public void parse_mark_success() throws DukeException {
        assertTrue(Parser.parse("mark 1") instanceof MarkCommand);
    }

    @Test
    public void parse_markNoInput_exceptionThrown() {
        try {
            Parser.parse("mark");
            fail();
        } catch (DukeException e) {
            assertEquals("Please enter an index to mark.", e.getMessage());
        }
    }

    @Test
    public void parse_markMoreThanOneInput_exceptionThrown() {
        try {
            Parser.parse("mark 2 3");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid Input.", e.getMessage());
        }
    }

    @Test
    public void parse_markNotIntInput_exceptionThrown() {
        try {
            Parser.parse("mark z");
            fail();
        } catch (DukeException e) {
            assertEquals("I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

    @Test
    public void parse_unmark_success() throws DukeException {
        assertTrue(Parser.parse("unmark 1") instanceof UnmarkCommand);
    }

    @Test
    public void parse_unmarkNoInput_exceptionThrown() {
        try {
            Parser.parse("unmark");
            fail();
        } catch (DukeException e) {
            assertEquals("Please enter an index to unmark.", e.getMessage());
        }
    }

    @Test
    public void parse_unmarkMoreThanOneInput_exceptionThrown() {
        try {
            Parser.parse("unmark 2 3");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid Input.", e.getMessage());
        }
    }

    @Test
    public void parse_unmarkNotIntInput_exceptionThrown() {
        try {
            Parser.parse("unmark z");
            fail();
        } catch (DukeException e) {
            assertEquals("I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

    @Test
    public void parse_delete_success() throws DukeException {
        assertTrue(Parser.parse("delete 1") instanceof DeleteCommand);
    }

    @Test
    public void parse_deleteNoInput_exceptionThrown() {
        try {
            Parser.parse("delete");
            fail();
        } catch (DukeException e) {
            assertEquals("Please enter an index to delete.", e.getMessage());
        }
    }

    @Test
    public void parse_deleteMoreThanOneInput_exceptionThrown() {
        try {
            Parser.parse("delete 2 3");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid Input.", e.getMessage());
        }
    }

    @Test
    public void parse_deleteNotIntInput_exceptionThrown() {
        try {
            Parser.parse("delete z");
            fail();
        } catch (DukeException e) {
            assertEquals("I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

    @Test
    public void parse_bye_success() throws DukeException {
        assertTrue(Parser.parse("bye") instanceof ByeCommand);
    }

    @Test
    public void parse_byeInvalid_exceptionThrown() {
        try {
            Parser.parse("bye hi");
            fail();
        } catch (DukeException e) {
            assertEquals("I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

    @Test
    public void parse_list_success() throws DukeException {
        assertTrue(Parser.parse("list") instanceof ListCommand);
    }

    @Test
    public void parse_listInvalid_exceptionThrown() {
        try {
            Parser.parse("list bye");
            fail();
        } catch (DukeException e) {
            assertEquals("I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

    @Test
    public void parse_todo_success() throws DukeException {
        assertTrue(Parser.parse("todo sleep") instanceof TodoCommand);
    }

    @Test
    public void parse_todoNoInput_exceptionThrown() {
        try {
            Parser.parse("todo");
            fail();
        } catch (DukeException e) {
            assertEquals("Please enter a task todo.", e.getMessage());
        }
    }

    @Test
    public void parse_event_success() throws DukeException {
        assertTrue(Parser.parse("event sleep /at 2022-8-22 1000") instanceof EventCommand);
    }

    @Test
    public void parse_eventNoTime_exceptionThrown() {
        try {
            Parser.parse("event sleep");
            fail();
        } catch (DukeException e) {
            assertEquals("Please use /at to specify event time.", e.getMessage());
        }
    }

    @Test
    public void parse_eventNoInput_exceptionThrown() {
        try {
            Parser.parse("event");
            fail();
        } catch (DukeException e) {
            assertEquals("Please enter an event.", e.getMessage());
        }
    }

    @Test
    public void parse_deadline_success() throws DukeException {
        assertTrue(Parser.parse("event sleep /by 2022-8-22 1000") instanceof DeadlineCommand);
    }

    @Test
    public void parse_deadlineNoTime_exceptionThrown() {
        try {
            Parser.parse("deadline fight");
            fail();
        } catch (DukeException e) {
            assertEquals("Please use /by to specify event time.", e.getMessage());
        }
    }

    @Test
    public void parse_deadlineNoInput_exceptionThrown() {
        try {
            Parser.parse("deadline");
            fail();
        } catch (DukeException e) {
            assertEquals("Please enter a deadline.", e.getMessage());
        }
    }

    @Test
    public void parse_date_success() throws DukeException {
        assertTrue(Parser.parse("date 2022-08-22") instanceof DateCommand);
    }

    @Test
    public void parse_dateInvalid_exceptionThrown() {
        try {
            Parser.parse("date 1");
            fail();
        } catch (DukeException e) {
            assertEquals("Please enter date in yyyy-M-d format.", e.getMessage());
        }
    }

    @Test
    public void parse_dateNoInput_exceptionThrown() {
        try {
            Parser.parse("date");
            fail();
        } catch (DukeException e) {
            assertEquals("Please enter a date.", e.getMessage());
        }
    }
}
