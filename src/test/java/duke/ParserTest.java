package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeInvalidDeadlineSeparatorException;
import duke.exception.DukeInvalidEventSeparatorException;
import duke.task.TaskList;

public class ParserTest {
    @Test
    public void parser_validDeadline() {
        try {
            TaskList tasks = new TaskList(new ArrayList<>());
            Ui ui = new Ui();
            Storage storage = new Storage("./data/data.txt");
            String input = "deadline Valid Deadline /by 2009-09-12 1300";
            Command c = Parser.parse(input);

            String expected = "Got it. I've added this task:\n"
                    + "[D][ ] Valid Deadline (by: 2009 Sep 12 01:00PM)\n"
                    + "Now you have 1 tasks in the list.\n";

            assertEquals(expected, c.execute(tasks, ui, storage));
        } catch (DukeException e) {
            fail("Not suppose to throw exception");
        }
    }

    @Test
    public void parser_validEvent() {
        try {
            TaskList tasks = new TaskList(new ArrayList<>());
            Ui ui = new Ui();
            Storage storage = new Storage("./data/data.txt");
            String input = "Event Valid Event /at 2009-09-12 1300";
            Command c = Parser.parse(input);

            String expected = "Got it. I've added this task:\n"
                    + "[E][ ] Valid Event (at: 2009 Sep 12 01:00PM)\n"
                    + "Now you have 1 tasks in the list.\n";

            assertEquals(expected, c.execute(tasks, ui, storage));
        } catch (DukeException e) {
            fail("Not suppose to throw exception");
        }
    }

    @Test
    public void parser_validToDo() {
        try {
            TaskList tasks = new TaskList(new ArrayList<>());
            Ui ui = new Ui();
            Storage storage = new Storage("./data/data.txt");
            String input = "Todo Valid Todo";
            Command c = Parser.parse(input);

            String expected = "Got it. I've added this task:\n"
                    + "[T][ ] Valid Todo\n"
                    + "Now you have 1 tasks in the list.\n";

            assertEquals(expected, c.execute(tasks, ui, storage));
        } catch (DukeException e) {
            fail("Not suppose to throw exception");
        }
    }

    @Test
    public void parser_invalidDeadlineDescription_exceptionThrown() {
        try {
            TaskList tasks = new TaskList(new ArrayList<>());
            Ui ui = new Ui();
            Storage storage = new Storage("./data/data.txt");
            String input = "deadline invalid Deadline /at 2009-09-12 1300";
            Command c = Parser.parse(input);
            fail();
        } catch (DukeInvalidDeadlineSeparatorException e) {
            assertTrue(true);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parser_invalidEventDescription_exceptionThrown() {
        try {
            TaskList tasks = new TaskList(new ArrayList<>());
            Ui ui = new Ui();
            Storage storage = new Storage("./data/data.txt");
            String input = "Event Valid Event /by 2009-09-12 1300";
            Command c = Parser.parse(input);
            fail();
        } catch (DukeInvalidEventSeparatorException e) {
            assertTrue(true);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parser_invalidDeadlineDate_exceptionThrown() {
        try {
            TaskList tasks = new TaskList(new ArrayList<>());
            Ui ui = new Ui();
            Storage storage = new Storage("./data/data.txt");
            String input = "deadline invalid Deadline /by 2009-09-122 13000";
            Command c = Parser.parse(input);
            fail();
        } catch (DukeException e) {
            String expected = "Please format date and time in YYYY-MM-DD hhmm.";
            assertEquals(expected, e.getMessage());
        }
    }

    @Test
    public void parser_invalidEventDate_exceptionThrown() {
        try {
            TaskList tasks = new TaskList(new ArrayList<>());
            Ui ui = new Ui();
            Storage storage = new Storage("./data/data.txt");
            String input = "event invalid event /at 2009-09-122 13000";
            Command c = Parser.parse(input);
            fail();
        } catch (DukeException e) {
            String expected = "Please format date and time in YYYY-MM-DD hhmm.";
            assertEquals(expected, e.getMessage());
        }
    }
}
