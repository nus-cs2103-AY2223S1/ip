package raiden;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import raiden.parser.DateParser;

public class DateParserTest {
    @Test
    public void parse_stringToDate_success() throws Exception {
        assertEquals(LocalDateTime.parse("2022-12-03T10:15:00"),
                DateParser.parseStringToDate("03 12 2022 10:15"));
        assertEquals(LocalDateTime.parse("2022-02-28T00:00:00"),
                DateParser.parseStringToDate("28 02 2022"));
        assertEquals(LocalDateTime.parse("2022-12-03T10:15:00"),
                DateParser.parseStringToDate("03-12-2022 10:15"));
        assertEquals(LocalDateTime.parse("2022-02-28T00:00:00"),
                DateParser.parseStringToDate("28-02-2022"));
        assertEquals(LocalDateTime.parse("2022-12-03T10:15:00"),
                DateParser.parseStringToDate("03/12/2022 10:15"));
        assertEquals(LocalDateTime.parse("2022-02-28T00:00:00"),
                DateParser.parseStringToDate("28/02/2022"));
    }

    @Test
    public void display_dateToString_success() throws Exception {
        assertEquals("Dec 03 2022 10:15",
                DateParser.parseDateToString(LocalDateTime.parse("2022-12-03T10:15:00")));
        assertEquals("Feb 28 2022 00:00",
                DateParser.parseDateToString(LocalDateTime.parse("2022-02-28T00:00:00")));
        assertEquals("Aug 08 2023 18:59",
                DateParser.parseDateToString(LocalDateTime.parse("2023-08-08T18:59:00")));
    }

    @Test
    public void display_dateToCommandString_success() throws Exception {
        assertEquals("03 12 2022 10:15",
                DateParser.parseDateToCommand(LocalDateTime.parse("2022-12-03T10:15:00")));
        assertEquals("28 02 2022 00:00",
                DateParser.parseDateToCommand(LocalDateTime.parse("2022-02-28T00:00:00")));
        assertEquals("08 08 2023 18:59",
                DateParser.parseDateToCommand(LocalDateTime.parse("2023-08-08T18:59:00")));
    }

    @Test
    public void parse_stringToDate_exceptionThrown() throws Exception {
        try {
            assertEquals(LocalDateTime.parse("2000-08-08T07:00:00"),
                    DateParser.parseStringToDate("08 Aug 2022 0700"));
            fail();
        } catch (DateTimeException e) {
            assertEquals("Text '08 Aug 2022 0700' could not be parsed, unparsed text found at index 0",
                    e.getMessage());
        }
    }

}
