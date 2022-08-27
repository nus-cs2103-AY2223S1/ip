import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import parser.DateTimeParser;

public class DateTimeParserTest {
    @Test
    public void testChangeStringToParsingDateTime() {
        assertEquals(LocalDateTime.of(2022, 9, 15, 6, 54),
                DateTimeParser.changeStringToParsingDateTime("Sep 15 2022 06:54 AM"));
    }

    @Test
    public void testChangeStringToReadingDateTime() {
        assertEquals(LocalDateTime.of(2022, 9, 15, 6, 54),
                DateTimeParser.changeStringToReadingDateTime("15-Sep-2022 06:54 AM"));
    }

    @Test
    public void testChangeDateTimeFormat() {
        assertEquals("15-Sep-2022 06:54 AM",
                DateTimeParser.changeDateTimeFormat(LocalDateTime.of(2022, 9, 15, 6, 54)));
    }
}
