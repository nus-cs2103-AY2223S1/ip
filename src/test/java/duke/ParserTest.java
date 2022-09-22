package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.exception.DukeException;
import duke.exception.InvalidInput;
import duke.exception.UnknownCommand;


public class ParserTest {

    @Test
    public void listTest() {
        Parser parser = new Parser();
        try {
            Command command = parser.parse("list");
            assertEquals(new ListCommand().getCommand(), command.getCommand());
        } catch (DukeException e) {
            fail(e);
        }
    }

    @Test
    public void markTest() {
        Parser parser = new Parser();
        try {
            Command command = parser.parse("mark 1");
            assertEquals(new MarkCommand("1", true).toString(), command.toString());
        } catch (DukeException e) {
            fail(e);
        }
    }

    @Test
    public void unmarkTest() {
        Parser parser = new Parser();
        try {
            Command command = parser.parse("unmark 1");
            assertEquals(new MarkCommand("1", false).toString(), command.toString());
        } catch (DukeException e) {
            fail(e);
        }
    }

    @Test
    public void addTodoTest() {
        Parser parser = new Parser();
        try {
            Command command = parser.parse("todo test test");
            assertEquals(new AddCommand("T", "test test", null).toString(), command.toString());
        } catch (DukeException e) {
            fail(e);
        }
    }

    @Test
    public void addTodoTest_noDescription() {
        Parser parser = new Parser();
        try {
            parser.parse("todo");
            fail("An exception should be thrown");
        } catch (DukeException e) {
            assertEquals(new InvalidInput("The description cannot be empty.").toString(), e.toString());
        }
    }

    @Test
    public void addEventTest() {
        Parser parser = new Parser();
        try {
            Command command = parser.parse("event test test /at 2019-10-15");
            LocalDate date = LocalDate.parse("2019-10-15");
            assertEquals(new AddCommand("E", "test test", date).toString(), command.toString());
        } catch (DukeException e) {
            fail(e);
        }
    }

    @Test
    public void addEventTest_invalidInput() {
        Parser parser = new Parser();
        // no /at
        try {
            parser.parse("event test test");
            fail("An exception should be thrown");
        } catch (DukeException e) {
            assertEquals(new InvalidInput("Ensure input format is correct.").toString(), e.toString());
        }
        // no description
        try {
            parser.parse("event /at 2019-10-15");
            fail("An exception should be thrown");
        } catch (DukeException e) {
            assertEquals(new InvalidInput("The description cannot be empty.").toString(), e.toString());
        }
        // invalid date format
        try {
            parser.parse("event test test /at 2019/10/15");
            fail("An exception should be thrown");
        } catch (DukeException e) {
            assertEquals(new InvalidInput("Date format should be yyyy-mm-dd").toString(), e.toString());
        }
    }

    @Test
    public void addDeadlineTest() {
        Parser parser = new Parser();
        try {
            Command command = parser.parse("deadline test test /by 2019-10-15");
            LocalDate date = LocalDate.parse("2019-10-15");
            assertEquals(new AddCommand("D", "test test", date).toString(), command.toString());
        } catch (DukeException e) {
            fail(e);
        }
    }

    @Test
    public void addDeadlineTest_invalidInput() {
        Parser parser = new Parser();
        // no /at
        try {
            parser.parse("deadline test test");
            fail("An exception should be thrown");
        } catch (DukeException e) {
            assertEquals(new InvalidInput("Ensure input format is correct.").toString(), e.toString());
        }
        // no description
        try {
            parser.parse("deadline /by 2019-10-15");
            fail("An exception should be thrown");
        } catch (DukeException e) {
            assertEquals(new InvalidInput("The description cannot be empty.").toString(), e.toString());
        }
        // invalid date format
        try {
            parser.parse("deadline test test /by 2019/10/15");
            fail("An exception should be thrown");
        } catch (DukeException e) {
            assertEquals(new InvalidInput("Date format should be yyyy-mm-dd").toString(), e.toString());
        }
    }

    @Test
    public void deleteTest() {
        Parser parser = new Parser();
        try {
            Command command = parser.parse("delete 1");
            assertEquals(new DeleteCommand("1").getCommand(), command.getCommand());
        } catch (DukeException e) {
            fail(e);
        }
    }

    @Test
    public void byeTest() {
        Parser parser = new Parser();
        try {
            Command command = parser.parse("bye");
            assertEquals(new ExitCommand().getCommand(), command.getCommand());
        } catch (DukeException e) {
            fail(e);
        }
    }

    @Test
    public void findTest() {
        Parser parser = new Parser();
        try {
            Command command = parser.parse("find test");
            assertEquals(new FindCommand("test").getCommand(), command.getCommand());
        } catch (DukeException e) {
            fail(e);
        }
    }

    @Test
    public void invalidCommandTest() {
        Parser parser = new Parser();
        try {
            parser.parse("test");
            fail("An exception should be thrown");
        } catch (DukeException e) {
            assertEquals(new UnknownCommand().toString(), e.toString());
        }
    }

}
