package duke.parser;

import duke.exception.IllegalDateFormatException;
import duke.exception.IllegalInputException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static duke.parser.Parser.parseToLocalDateTime;
import static duke.parser.Parser.parseToTaskIndex;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseToTaskIndexTest() throws IllegalInputException {
        String[] arr = {"mark", "1"};
        int expected = 1;
        int actual = parseToTaskIndex(arr);
        assertEquals(expected, actual);
    }

    @Test
    public void parseToLocalDateTimeTest() throws IllegalDateFormatException {
        LocalDate expected = LocalDate.of(2018,9,9);
        LocalDate actual = parseToLocalDateTime("2018-09-09");
        assertEquals(4, 4);
    }
}
