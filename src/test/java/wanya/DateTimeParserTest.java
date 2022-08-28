package wanya;

import wanya.parser.DateTimeParser;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateTimeParserTest {
    @Test
    public void getDateTime_validDate_correctDateTime() {
        String date = "2022-08-12";
        LocalDateTime dateTime = DateTimeParser.getDateTime(date);
        assertEquals(LocalDateTime.of(2022, 8, 12, 00, 00),
                dateTime);
    }

    @Test
    public void getDateTime_invalidDate_exceptionThrown() {
        String date = "2022-13-12";
        assertThrows(DateTimeException.class,
                () -> DateTimeParser.getDateTime(date));
    }


    @Test
    public void getDateTime_validDateTime_correctDateTime() {
        String date = "2022-08-12 13:23";
        LocalDateTime dateTime = DateTimeParser.getDateTime(date);
        assertEquals(LocalDateTime.of(2022, 8, 12, 13, 23),
                dateTime);
    }

    @Test
    public void getDateTime_invalidDateTime_exceptionThrown() {
        String date = "2022-08-12 25:12";
        assertThrows(DateTimeException.class,
                () -> DateTimeParser.getDateTime(date));
    }

    @Test
    public void getDateTimeString_validDate_correctFormat() {
        String date = "2022-08-12";
        LocalDateTime dateTime = DateTimeParser.getDateTime(date);
        assertEquals("Aug 12 2022 12:00 AM",
                DateTimeParser.getDateTimeString(dateTime));
    }

    @Test
    public void getDateTimeString_validDateTime_correctFormat() {
        String date = "2022-08-12 23:12";
        LocalDateTime dateTime = DateTimeParser.getDateTime(date);
        assertEquals("Aug 12 2022 11:12 PM",
                DateTimeParser.getDateTimeString(dateTime));
    }

    @Test
    public void getDateTimeStorage_validDate_correctFormat() {
        String date = "2022-08-12";
        LocalDateTime dateTime = DateTimeParser.getDateTime(date);
        assertEquals("2022-08-12 00:00",
                DateTimeParser.getDateTimeStorage(dateTime));
    }

    @Test
    public void getDateTimeStorage_validDateTime_correctFormat() {
        String date = "2022-09-12 12:13";
        LocalDateTime dateTime = DateTimeParser.getDateTime(date);
        assertEquals("2022-09-12 12:13",
                DateTimeParser.getDateTimeStorage(dateTime));
    }
}
