package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.OnCommand;
import duke.command.UnmarkCommand;

public class ParserTest {
    @Test
    public void parse_notACommand_exceptionThrown() {
        try {
            Parser.parse("hello");
            fail();
        } catch (DukeException e) {
            assertEquals("I'm sorry, but I don't know what that means :-(",
                    e.getMessage());
        }
    }

    @Test
    public void parse_bye_success() throws DukeException {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
    }

    @Test
    public void parse_list_success() throws DukeException {
        assertTrue(Parser.parse("list") instanceof ListCommand);
    }

    @Test
    public void parse_markInteger_success() throws DukeException {
        assertTrue(Parser.parse("mark 1") instanceof MarkCommand);
    }

    @Test
    public void parse_markNothing_exceptionThrown() {
        try {
            Parser.parse("mark");
            fail();
        } catch (DukeException e) {
            assertEquals("Specify which task to mark with a single integer.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_markNonInteger_exceptionThrown() {
        try {
            Parser.parse("mark food");
            fail();
        } catch (DukeException e) {
            assertEquals("Specify which task to mark with a single integer.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_unmarkInteger_success() throws DukeException {
        assertTrue(Parser.parse("unmark 1") instanceof UnmarkCommand);
    }

    @Test
    public void parse_unmarkNothing_exceptionThrown() {
        try {
            Parser.parse("unmark");
            fail();
        } catch (DukeException e) {
            assertEquals("Specify which task to unmark with a single integer.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_unmarkNonInteger_exceptionThrown() {
        try {
            Parser.parse("unmark food");
            fail();
        } catch (DukeException e) {
            assertEquals("Specify which task to unmark with a single integer.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_todoDescription_success() throws DukeException {
        assertTrue(Parser.parse("todo homework") instanceof AddCommand);
    }

    @Test
    public void parse_todoNoDescription_exceptionThrown() {
        try {
            Parser.parse("todo");
            fail();
        } catch (DukeException e) {
            assertEquals("The description of a todo cannot be empty.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_deadlineDescriptionBy_success() throws DukeException {
        assertTrue(Parser.parse("deadline sleep /by 2022-09-01 0000") instanceof AddCommand);
        assertTrue(Parser.parse("deadline sleep /by 2022-09-01") instanceof AddCommand);
        assertTrue(Parser.parse("deadline sleep /by 2022-09-01 afternoon") instanceof AddCommand);
        assertTrue(Parser.parse("deadline sleep /by tonight") instanceof AddCommand);
    }

    @Test
    public void parse_deadlineDescriptionNoBy_exceptionThrown() {
        try {
            Parser.parse("deadline sleep");
            fail();
        } catch (DukeException e) {
            assertEquals("Use /by to provide when a deadline must be completed.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_deadlineDescriptionByNothing_exceptionThrown() {
        try {
            Parser.parse("deadline sleep /by");
            fail();
        } catch (DukeException e) {
            assertEquals("Use /by to provide when a deadline must be completed.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_deadlineNoDescription_exceptionThrown() {
        try {
            Parser.parse("deadline /by tomorrow");
            fail();
        } catch (DukeException e) {
            assertEquals("The description of a deadline cannot be empty.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_deadlineNoDescriptionNoBy_exceptionThrown() {
        try {
            Parser.parse("deadline");
            fail();
        } catch (DukeException e) {
            assertEquals("The description of a deadline cannot be empty.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_eventDescriptionAt_success() throws DukeException {
        assertTrue(Parser.parse("event sleep /at 2022-09-01 0000") instanceof AddCommand);
        assertTrue(Parser.parse("event sleep /at 2022-09-01") instanceof AddCommand);
        assertTrue(Parser.parse("event sleep /at 2022-09-01 afternoon") instanceof AddCommand);
        assertTrue(Parser.parse("event sleep /at tonight") instanceof AddCommand);
    }

    @Test
    public void parse_eventDescriptionNoAt_exceptionThrown() {
        try {
            Parser.parse("event sleep");
            fail();
        } catch (DukeException e) {
            assertEquals("Use /at to provide when an event occurs.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_eventDescriptionAtNothing_exceptionThrown() {
        try {
            Parser.parse("event sleep /at");
            fail();
        } catch (DukeException e) {
            assertEquals("Use /at to provide when an event occurs.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_eventNoDescription_exceptionThrown() {
        try {
            Parser.parse("event /at tomorrow");
            fail();
        } catch (DukeException e) {
            assertEquals("The description of an event cannot be empty.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_eventNoDescriptionNoAt_exceptionThrown() {
        try {
            Parser.parse("event");
            fail();
        } catch (DukeException e) {
            assertEquals("The description of an event cannot be empty.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_deleteInteger_success() throws DukeException {
        assertTrue(Parser.parse("delete 1") instanceof DeleteCommand);
    }

    @Test
    public void parse_deleteNothing_exceptionThrown() {
        try {
            Parser.parse("delete");
            fail();
        } catch (DukeException e) {
            assertEquals("Specify which task to delete with a single integer.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_deleteNonInteger_exceptionThrown() {
        try {
            Parser.parse("delete food");
            fail();
        } catch (DukeException e) {
            assertEquals("Specify which task to delete with a single integer.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_onDate_success() throws DukeException {
        assertTrue(Parser.parse("on 2022-10-01") instanceof OnCommand);
    }

    @Test
    public void parse_onNothing_exceptionThrown() {
        try {
            Parser.parse("on");
            fail();
        } catch (DukeException e) {
            assertEquals("Specify the date to check with yyyy-MM-dd.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_onInvalidDate_exceptionThrown() {
        try {
            Parser.parse("on 22-5-4");
            fail();
        } catch (DukeException e) {
            assertEquals("Specify the date to check with yyyy-MM-dd.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_findSomething_success() throws DukeException {
        assertTrue(Parser.parse("find me") instanceof FindCommand);
    }

    @Test
    public void parse_findNothing_exceptionThrown() {
        try {
            Parser.parse("find");
            fail();
        } catch (DukeException e) {
            assertEquals("Include the keyword you want to find.",
                    e.getMessage());
        }
    }
}
