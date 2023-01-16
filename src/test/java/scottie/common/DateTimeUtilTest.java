package scottie.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class DateTimeUtilTest {
    private static final LocalDateTime SAMPLE_DATE_TIME = LocalDateTime.of(2005, 11, 28, 15, 25);
    private static final LocalDate SAMPLE_DATE = LocalDate.of(2041, 1, 3);

    @Test
    void testParseCompactDateTime() {
        assertEquals(SAMPLE_DATE_TIME, DateTimeUtil.parseCompactDateTime("28/11/05 1525"));
    }

    @Test
    void testParseCompactDate() {
        assertEquals(SAMPLE_DATE, DateTimeUtil.parseCompactDateTime("3/1/41"));
    }

    @Test
    void testFormatCompactDateTime() {
        assertEquals("28/11/05 1525", DateTimeUtil.formatCompactDateTime(SAMPLE_DATE_TIME));
    }

    @Test
    void testFormatCompactDate() {
        assertEquals("3/1/41", DateTimeUtil.formatCompactDateTime(SAMPLE_DATE));
    }

    @Test
    void testFormatPrettyDateTime() {
        assertEquals("Nov 28 2005 3:25PM", DateTimeUtil.formatPrettyDateTime(SAMPLE_DATE_TIME));
    }

    @Test
    void testFormatPrettyDate() {
        assertEquals("Jan 3 2041", DateTimeUtil.formatPrettyDateTime(SAMPLE_DATE));
    }

    @Test
    void testLocalDateTimeToLocalDateTime() {
        assertEquals(LocalDateTime.of(2005, 11, 28, 15, 25), DateTimeUtil.toLocalDateTime(SAMPLE_DATE_TIME));
    }

    @Test
    void testLocalDateToLocalDateTime() {
        assertEquals(LocalDateTime.of(2041, 1, 3, 0, 0), DateTimeUtil.toLocalDateTime(SAMPLE_DATE));
    }
}
