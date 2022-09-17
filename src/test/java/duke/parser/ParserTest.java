package duke.parser;

import static duke.parser.Parser.parseToLocalDateTime;
import static duke.parser.Parser.parseToTaskIndex;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

import duke.exception.IllegalDateFormatException;
import duke.exception.IllegalInputException;

public class ParserTest {
    @Test
    public void parseToTaskIndexTest() throws IllegalInputException {
        String str = "1";
        int expected = 1;
        int actual = parseToTaskIndex(str);
        assertEquals(expected, actual);
    }

    @Test
    public void parseToLocalDateTimeTest() throws IllegalDateFormatException {
        String input = "2022-12-21T10:30";
        LocalDateTime localDateTime = parseToLocalDateTime(input);
        Month month = localDateTime.getMonth();
        assertEquals(12,localDateTime.of);
    }

}
