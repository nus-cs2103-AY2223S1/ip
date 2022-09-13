package nyanduke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import nyanduke.command.AddCommand;
import nyanduke.command.DeleteCommand;
import nyanduke.command.ExitCommand;
import nyanduke.command.FindCommand;
import nyanduke.command.ListCommand;
import nyanduke.command.MarkCommand;
import nyanduke.command.OnCommand;
import nyanduke.command.UnmarkCommand;

public class ParserTest {
    @Test
    public void parse_notACommand_exceptionThrown() {
        try {
            Parser.parse("hello");
            fail();
        } catch (NyanDukeException e) {
            assertEquals("IDK what that means :c",
                    e.getMessage());
        }
    }

    @Test
    public void parse_bye_success() throws NyanDukeException {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
    }

    @Test
    public void parse_list_success() throws NyanDukeException {
        assertTrue(Parser.parse("list") instanceof ListCommand);
    }

    @Test
    public void parse_markInteger_success() throws NyanDukeException {
        assertTrue(Parser.parse("mark 1") instanceof MarkCommand);
    }

    @Test
    public void parse_markNothing_exceptionThrown() {
        try {
            Parser.parse("mark");
            fail();
        } catch (NyanDukeException e) {
            assertEquals("Specify which tasks to mark with integers.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_markNonInteger_exceptionThrown() {
        try {
            Parser.parse("mark food");
            fail();
        } catch (NyanDukeException e) {
            assertEquals("Specify which tasks to mark with integers.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_unmarkInteger_success() throws NyanDukeException {
        assertTrue(Parser.parse("unmark 1") instanceof UnmarkCommand);
    }

    @Test
    public void parse_unmarkNothing_exceptionThrown() {
        try {
            Parser.parse("unmark");
            fail();
        } catch (NyanDukeException e) {
            assertEquals("Specify which tasks to unmark with integers.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_unmarkNonInteger_exceptionThrown() {
        try {
            Parser.parse("unmark food");
            fail();
        } catch (NyanDukeException e) {
            assertEquals("Specify which tasks to unmark with integers.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_todoDescription_success() throws NyanDukeException {
        assertTrue(Parser.parse("todo homework") instanceof AddCommand);
    }

    @Test
    public void parse_todoNoDescription_exceptionThrown() {
        try {
            Parser.parse("todo");
            fail();
        } catch (NyanDukeException e) {
            assertEquals("The description of a todo cannot be empty.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_deadlineDescriptionBy_success() throws NyanDukeException {
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
        } catch (NyanDukeException e) {
            assertEquals("Use /by to provide when a deadline must be completed.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_deadlineDescriptionByNothing_exceptionThrown() {
        try {
            Parser.parse("deadline sleep /by");
            fail();
        } catch (NyanDukeException e) {
            assertEquals("Use /by to provide when a deadline must be completed.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_deadlineNoDescription_exceptionThrown() {
        try {
            Parser.parse("deadline /by tomorrow");
            fail();
        } catch (NyanDukeException e) {
            assertEquals("The description of a deadline cannot be empty.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_deadlineNoDescriptionNoBy_exceptionThrown() {
        try {
            Parser.parse("deadline");
            fail();
        } catch (NyanDukeException e) {
            assertEquals("The description of a deadline cannot be empty.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_eventDescriptionAt_success() throws NyanDukeException {
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
        } catch (NyanDukeException e) {
            assertEquals("Use /at to provide when an event occurs.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_eventDescriptionAtNothing_exceptionThrown() {
        try {
            Parser.parse("event sleep /at");
            fail();
        } catch (NyanDukeException e) {
            assertEquals("Use /at to provide when an event occurs.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_eventNoDescription_exceptionThrown() {
        try {
            Parser.parse("event /at tomorrow");
            fail();
        } catch (NyanDukeException e) {
            assertEquals("The description of an event cannot be empty.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_eventNoDescriptionNoAt_exceptionThrown() {
        try {
            Parser.parse("event");
            fail();
        } catch (NyanDukeException e) {
            assertEquals("The description of an event cannot be empty.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_deleteInteger_success() throws NyanDukeException {
        assertTrue(Parser.parse("delete 1") instanceof DeleteCommand);
    }

    @Test
    public void parse_deleteNothing_exceptionThrown() {
        try {
            Parser.parse("delete");
            fail();
        } catch (NyanDukeException e) {
            assertEquals("Specify which tasks to delete with integers.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_deleteNonInteger_exceptionThrown() {
        try {
            Parser.parse("delete food");
            fail();
        } catch (NyanDukeException e) {
            assertEquals("Specify which tasks to delete with integers.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_onDate_success() throws NyanDukeException {
        assertTrue(Parser.parse("on 2022-10-01") instanceof OnCommand);
    }

    @Test
    public void parse_onNothing_exceptionThrown() {
        try {
            Parser.parse("on");
            fail();
        } catch (NyanDukeException e) {
            assertEquals("Specify the date to check with yyyy-MM-dd.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_onInvalidDate_exceptionThrown() {
        try {
            Parser.parse("on 22-5-4");
            fail();
        } catch (NyanDukeException e) {
            assertEquals("Specify the date to check with yyyy-MM-dd.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_findSomething_success() throws NyanDukeException {
        assertTrue(Parser.parse("find me") instanceof FindCommand);
    }

    @Test
    public void parse_findNothing_exceptionThrown() {
        try {
            Parser.parse("find");
            fail();
        } catch (NyanDukeException e) {
            assertEquals("Include the keyword you want to find.",
                    e.getMessage());
        }
    }
}
