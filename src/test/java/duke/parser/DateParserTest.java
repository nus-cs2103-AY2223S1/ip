package duke.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.parser.exceptions.InvalidDateException;

public class DateParserTest {
    @Test
    public void verifyDateFormat_correctFormat_success() {
        assertDoesNotThrow(() -> DateParser.verifyDateFormat("2022-04-02"));
    }

    @Test
    public void verifyDateFormat_wrongFormat_throwsInvalidDateException() {
        assertThrows(InvalidDateException.class, () -> DateParser.verifyDateFormat("4 April 2022"));
    }

    @Test
    public void getParsedDate_correctFormat_success() {
        assertEquals("2 Apr 2022", DateParser.getParsedDate("2022-04-02"));
    }
}
