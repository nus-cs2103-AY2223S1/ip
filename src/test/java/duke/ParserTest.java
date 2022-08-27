package duke;

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
        assertEquals("☹ OOPS!!! This is not a proper time format, please refer to command format information.",
                exception.getMessage());
        exception = assertThrows(DukeException.class, () -> {parser.parseDate("2021-04-38");});
        assertEquals("Text '2021-04-38' could not be parsed: Invalid value for DayOfMonth (valid values 1 - 28/31): 38",
                exception.getMessage());
        exception = assertThrows(DukeException.class, () -> {parser.parseDate("2021-14-30");});
        assertEquals("Text '2021-14-30' could not be parsed: Invalid value for MonthOfYear (valid values 1 - 12): 14",
                exception.getMessage());
    }

    @Test
    public void parseCommandTest() throws DukeException {
        //correct command string: add {task type} as no.{task index} task in the list
        assertEquals("todo 1", parser.parseCommand("todo eat dinner", taskList));
        assertEquals("deadline 2", parser.parseCommand("deadline CS2103 quiz /by 2022-08-27", taskList));
        assertEquals("deadline 3", parser.parseCommand("deadline DSA3102 quiz /by 2022-08-24", taskList));
        assertEquals("event 4", parser.parseCommand("event shopping /at 2022-08-27 to 2022-08-28", taskList));
        assertEquals("event 5", parser.parseCommand("event team meeting /at 2022-08-26 to 2022-08-27", taskList));
        assertEquals("event 6", parser.parseCommand("event working /at 2022-08-22 to 2022-08-26", taskList));
        assertEquals("todo 7", parser.parseCommand("todo sleep", taskList));

        //incorrect to do command string: no description found
        Exception exception = assertThrows(DukeException.class, () -> {parser.parseCommand("todo    ", taskList);});
        assertEquals("☹ OOPS!!! The description of a Todo task cannot be empty.", exception.getMessage());

        //incorrect deadline command string
        exception = assertThrows(DukeException.class, () -> {parser.parseCommand("deadline  quiz 2022-03-24", taskList);});
        assertEquals("☹ OOPS!!! The command not properly formatted.\n" +
                "Please follow the format: Deadline {description} /by {date}.", exception.getMessage());
        exception = assertThrows(DukeException.class, () -> {parser.parseCommand("deadline /by 2022-02-24", taskList);});
        assertEquals("☹ OOPS!!! The description of a Deadline task cannot be empty.", exception.getMessage());

        //incorrect event command string
        exception = assertThrows(DukeException.class, () -> {parser.parseCommand("event  drink 2022-03-24", taskList);});
        assertEquals("☹ OOPS!!! The command not properly formatted.\n" +
                "Please follow the format: Event {description} /at {date} to {date}.", exception.getMessage());
        exception = assertThrows(DukeException.class, () -> {parser.parseCommand("event /at 2022-02-24 to 2022-03-23", taskList);});
        assertEquals("☹ OOPS!!! The description of a Event task cannot be empty.", exception.getMessage());
        exception = assertThrows(DukeException.class, () -> {parser.parseCommand("event e /at 2022-02-24 to 2022-01-23", taskList);});
        assertEquals("☹ OOPS!!! The start date of a Event task cannot be after end date.", exception.getMessage());

        //correct mark, unmark, delete command
        assertEquals("mark 1", parser.parseCommand("mark 1", taskList));
        assertEquals("unmark 1", parser.parseCommand("unmark      1", taskList));
        assertEquals("unmark 2", parser.parseCommand("unmark  2", taskList));
        assertEquals("mark 3", parser.parseCommand("mark   3", taskList));
        assertEquals("delete 1", parser.parseCommand("delete 1", taskList));

        //incorrect mark, unmark, delete command
        exception = assertThrows(DukeException.class, () -> {parser.parseCommand("mark 8", taskList);});
        assertEquals("☹ OOPS!!! The task index exceeds task list size limit.",exception.getMessage());
        exception = assertThrows(DukeException.class, () -> {parser.parseCommand("unmark  0", taskList);});
        assertEquals("☹ OOPS!!! The task index exceeds task list size limit.",exception.getMessage());
        exception = assertThrows(DukeException.class, () -> {parser.parseCommand("delete     ", taskList);});
        assertEquals("☹ OOPS!!! The task index of a delete command cannot be empty.",exception.getMessage());

        //list, list date command
        assertEquals("list all", parser.parseCommand("list     ", taskList));
        assertEquals("list 2022-03-24", parser.parseCommand("list    24 03 2022", taskList));
        assertEquals("bye", parser.parseCommand("bye", taskList));

        // incorrect commands (not understandable by duke)
        exception = assertThrows(DukeException.class, () -> {parser.parseCommand("hello!", taskList);});
        assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(",exception.getMessage());
        exception = assertThrows(DukeException.class, () -> {parser.parseCommand("random words", taskList);});
        assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(",exception.getMessage());
    }
}
