package duke.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class ParserTest {
    private Parser parser = new Parser();

    @Test
    public void oneWrongStringTest() {
        try {
            parser.parse("hello");
        } catch (DukeException e) {
            assertEquals("Error encountered: Invalid input, please use only:\n"
                    + "todo, deadline, event with a task", e.toString());
        }
    }

    @Test
    public void moreThanOneWrongStringTest() {
        try {
            parser.parse("hello all");
        } catch (DukeException e) {
            assertEquals("Error encountered: Invalid input, please use only:\n"
                    + "todo, deadline, event with a task", e.toString());
        }
    }

    @Test
    public void toDoStringTest() {
        try {
            parser.parse("todo");
        } catch (DukeException e) {
            assertEquals("Error encountered: Invalid input, please use only:\n"
                    + "todo, deadline, event with a task", e.toString());
        }
    }

    @Test
    public void deadlineWithoutTimeStringTest() {
        try {
            parser.parse("deadline 1");
        } catch (DukeException e) {
            assertEquals("Error encountered: please input a time "
                + "(/at for events and /by for deadlines)", e.toString());
        }
    }

    @Test
    public void eventWithoutTimeStringTest() {
        try {
            parser.parse("event 1");
        } catch (DukeException e) {
            assertEquals("Error encountered: please input a time "
                + "(/at for events and /by for deadlines)", e.toString());
        }
    }

    @Test
    public void deadlineWithWrongTimeStringTest() {
        try {
            parser.parse("deadline 1 /at mon");
        } catch (DukeException e) {
            assertEquals("Error encountered: "
                + "Please use the specified date-time format: yyyy-MM-dd HH:mm, "
                + "or yyyy-MM-dd if you want the time to be 23:59", e.toString());
        }
    }
    @Test
    public void eventWithWrongTimeStringTest() {
        try {
            parser.parse("event 1 /by 1800");
        } catch (DukeException e) {
            assertEquals("Error encountered: "
                + "Please use the specified date-time format: yyyy-MM-dd HH:mm, "
                + "or yyyy-MM-dd if you want the time to be 23:59", e.toString());
        }
    }
}
