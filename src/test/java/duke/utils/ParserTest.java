package duke.utils;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private Parser parser = new Parser();

    @Test
    public void oneWrongStringTest() {
        try {
            parser.parse("hello");
        } catch (DukeException e) {
            assertEquals("( • ᴖ • ｡) Error encountered: Invalid input, please use only:\n" +
                    "todo, deadline, event with a task",e.toString());
        }
    }

    @Test
    public void moreThanOneWrongStringTest() {
        try {
            parser.parse("hello all");
        } catch (DukeException e) {
            assertEquals("( • ᴖ • ｡) Error encountered: Invalid input, please use only:\n" +
                    "todo, deadline, event with a task",e.toString());
        }
    }

    @Test
    public void ToDoStringTest() {
        try {
            parser.parse("todo");
        } catch (DukeException e) {
            assertEquals("( • ᴖ • ｡) Error encountered: Invalid input, please use only:\n" +
                    "todo, deadline, event with a task",e.toString());
        }
    }

    @Test
    public void DeadlineWithoutTimeStringTest() {
        try {
            parser.parse("deadline 1");
        } catch (DukeException e) {
            assertEquals("( • ᴖ • ｡) Error encountered: please input a time (/at for events and /by for deadlines)",e.toString());
        }
    }

    @Test
    public void EventWithoutTimeStringTest() {
        try {
            parser.parse("event 1");
        } catch (DukeException e) {
            assertEquals("( • ᴖ • ｡) Error encountered: please input a time (/at for events and /by for deadlines)",e.toString());
        }
    }

    @Test
    public void DeadlineWithWrongTimeStringTest() {
        try {
            parser.parse("deadline 1 /at mon");
        } catch (DukeException e) {
            assertEquals("( • ᴖ • ｡) Error encountered: Please use the specified date-time format: yyyy-MM-dd HH:mm, or yyyy-MM-dd if you want the time to be 23:59",e.toString());
        }
    }
    @Test
    public void EventWithWrongTimeStringTest() {
        try {
            parser.parse("event 1 /by 1800");
        } catch (DukeException e) {
            assertEquals("( • ᴖ • ｡) Error encountered: Please use the specified date-time format: yyyy-MM-dd HH:mm, or yyyy-MM-dd if you want the time to be 23:59",e.toString());
        }
    }
}
