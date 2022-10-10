package duke;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    Parser parser = new Parser();
    TaskList taskList = new TaskList();
    @Test
    public void parseDateTest() throws DukeException {
        //proper date format
        assertEquals(LocalDate.parse("2021-04-24"), parser.parseDate("2021 04 24"));
        assertEquals(LocalDate.parse("2021-04-24"), parser.parseDate("24 04 2021"));
        assertEquals(LocalDate.parse("2021-04-24"), parser.parseDate("2021-04-24"));
        assertEquals(LocalDate.parse("2021-04-24"), parser.parseDate("24-04-2021"));
        assertEquals(LocalDate.parse("2021-04-24"), parser.parseDate("2021/04/24"));
        assertEquals(LocalDate.parse("2021-04-24"), parser.parseDate("24/04/2021"));

        //not proper date format
        Exception exception = assertThrows(DukeException.class, () -> {parser.parseDate("2021 04");});
        assertEquals("OOPS!!! This is not a proper date format, please refer to command format information.",
                exception.getMessage());
        exception = assertThrows(DukeException.class, () -> {parser.parseDate("2021-04-38");});
        assertEquals("OOPS!!! This is not a proper date format, year, month or day value is invalid.",
                exception.getMessage());
        exception = assertThrows(DukeException.class, () -> {parser.parseDate("2021-14-30");});
        assertEquals("OOPS!!! This is not a proper date format, year, month or day value is invalid.",
                exception.getMessage());
    }

    @Test
    public void parserIncorrectAddTodoCommand() {
        Exception exception = assertThrows(DukeException.class, () -> {
            parser.parseCommandType(new String[] {"todo    ",});
        });
        assertEquals("OOPS!!! The description of a todo task cannot be empty.", exception.getMessage());
    }
    @Test
    public void parserIncorrectAddDeadlineCommand() {
        Exception exception = assertThrows(DukeException.class, () -> {
            parser.parseCommand("deadline  quiz 2022-03-24", taskList);
        });
        assertEquals("OOPS!!! The command is not properly formatted."
                + " Please follow the format: deadline {description} /by {date}.", exception.getMessage());
        exception = assertThrows(DukeException.class, () -> {
            parser.parseCommand("deadline /by 2022-02-24", taskList);
        });
        assertEquals("OOPS!!! The description of a deadline task cannot be empty.", exception.getMessage());
    }

    @Test
    public void parserIncorrectAddEventCommand() {
        Exception exception = assertThrows(DukeException.class, () -> {
            parser.parseCommand("event  drink 2022-03-24", taskList);
        });
        assertEquals("OOPS!!! The command is not properly formatted."
                + " Please follow the format: event {description} /at {date} to {date}.", exception.getMessage());
        exception = assertThrows(DukeException.class, () -> {
            parser.parseCommand("event /at 2022-02-24 to 2022-03-23", taskList);
        });
        assertEquals("OOPS!!! The description of a event task cannot be empty.", exception.getMessage());
        exception = assertThrows(DukeException.class, () -> {
            parser.parseCommand("event e /at 2022-02-24 to 2022-01-23", taskList);
        });
        assertEquals("OOPS!!! The start date of a Event task cannot be after end date.", exception.getMessage());
    }

    @Test
    public void parserIncorrectMarkCommand() {
        //incorrect mark, unmark, delete command
        Exception exception = assertThrows(DukeException.class, () -> {parser.parseCommand("mark 8", taskList);});
        assertEquals("OOPS!!! The task index exceeds task list size limit.",exception.getMessage());
        exception = assertThrows(DukeException.class, () -> {parser.parseCommand("unmark  0", taskList);});
        assertEquals("OOPS!!! The task index exceeds task list size limit.",exception.getMessage());
        exception = assertThrows(DukeException.class, () -> {parser.parseCommand("delete     ", taskList);});
        assertEquals("OOPS!!! The task index of a delete command can only be a positive integer and it "
                + "cannot be empty.",exception.getMessage());
    }

    @Test
    public void parseMarkCommandTest() throws DukeException {
        //correct command string: add {task type} as no.{task index} task in the list
        assertEquals("todo 1", parser.parseCommand("todo eat dinner", taskList));
        assertEquals("deadline 2", parser.parseCommand("deadline CS2103 quiz /by 2022-08-27", taskList));
        assertEquals("deadline 3", parser.parseCommand("deadline DSA3102 quiz /by 2022-08-24", taskList));
        assertEquals("event 4", parser.parseCommand("event shopping /at 2022-08-27 to 2022-08-28", taskList));
        assertEquals("event 5", parser.parseCommand("event team meeting /at 2022-08-26 to 2022-08-27", taskList));
        assertEquals("event 6", parser.parseCommand("event working /at 2022-08-22 to 2022-08-26", taskList));
        assertEquals("todo 7", parser.parseCommand("todo sleep", taskList));

        //correct mark, unmark, delete command
        assertEquals("mark 1", parser.parseCommand("mark 1", taskList));
        assertEquals("unmark 1", parser.parseCommand("unmark      1", taskList));
        assertEquals("unmark 2", parser.parseCommand("unmark  2", taskList));
        assertEquals("mark 3", parser.parseCommand("mark   3", taskList));
        assertEquals("delete [T][ ] eat dinner", parser.parseCommand("delete 1", taskList));

        //list, list date command
        assertEquals("list 0", parser.parseCommand("list     ", taskList));
        assertEquals("list 2022-03-24", parser.parseCommand("list    24 03 2022", taskList));
        assertEquals("bye", parser.parseCommand("bye", taskList));
    }


    @Test
    public void parseIncorrectCommandTest() {
        // incorrect commands (not understandable by duke)
        Exception exception = assertThrows(DukeException.class, () -> {parser.parseCommand("hello!", taskList);});
        assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(",exception.getMessage());
        exception = assertThrows(DukeException.class, () -> {parser.parseCommand("random words", taskList);});
        assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(",exception.getMessage());
    }
}
