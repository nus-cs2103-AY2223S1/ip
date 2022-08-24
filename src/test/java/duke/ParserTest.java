package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    void test1() throws DukeException {
        try {
            String commandType = Parser.getCommandType("todo   ");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    @Test
    void test2() {
        try {
            String commandType = Parser.getCommandType("idk what to type");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    @Test
    void test3() {
        try {
            String commandType = Parser.getCommandType("deadline submit project/by 22/2/22 17:00");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Date and Time is not of correct format!");
        }
    }

    @Test
    void test4() throws DukeException {
        LocalDateTime dateTime = Parser.parseDateTime("event proj meeting/at 25/08/2022 2000");
        assertEquals(dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")), "25/08/2022 2000");
    }
}
