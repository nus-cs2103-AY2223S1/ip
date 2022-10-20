package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.exception.IllegalDateFormatException;
import duke.exception.IllegalInputException;

public class ParserTest {

    @Test
    void parse() {
        String fullCommand = "help";
        try {
            Parser.parse(fullCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void parseSecondTest() {
        String fullCommand = "event dinner /at 2018-12-12 2015";
        try {
            Parser.parse(fullCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void parseThirdTest() {
        String fullCommand = "edit 1 todo run";
        try {
            Parser.parse(fullCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void parseToTaskIndex() throws IllegalInputException {
        String str = "1";
        int expected = 1;
        int actual = Parser.parseToTaskIndex(str);
        assertEquals(expected, actual);
    }

    @Test
    void parseToLocalDateTime() throws IllegalDateFormatException {
        LocalDateTime d1 = Parser.parseToLocalDateTime("Oct 12 2018 16:30");
        LocalDateTime d2 = Parser.parseToLocalDateTime("2018-10-12 1630");
        assertEquals(d1, d2);
    }

}
