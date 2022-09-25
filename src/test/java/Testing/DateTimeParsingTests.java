package Testing;

import dukeprogram.parser.DateTimeParser;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class DateTimeParsingTests {

    /**
     * Tests if parsing DD/MMM/YYYY is correct
     */
    @Test
    public void dateTimeParser_slashDelimiter_twoDigitDateWordMonthFullYear() {
        LocalDateTime localDateTime = DateTimeParser.parse("08/Dec/2000");
        assertEquals(LocalDateTime.of(2000, 12, 8, 12, 0), localDateTime);
    }

    /**
     * Tests if parsing DD/MMM/YY is correct
     */
    @Test
    public void dateTimeParser_slashDelimiter_twoDigitDateWordMonthHalfYear() {
        LocalDateTime localDateTime = DateTimeParser.parse("08/Dec/07");
        assertEquals(LocalDateTime.of(2007, 12, 8, 12, 0), localDateTime);

        LocalDateTime localDateTime2 = DateTimeParser.parse("08/Dec/12");
        assertEquals(LocalDateTime.of(2012, 12, 8, 12, 0), localDateTime2);
    }

    /**
     * Tests if parsing DD/MMM is correct
     */
    @Test
    public void dateTimeParser_slashDelimiter_twoDigitDateWordMonthNoYear() {
        LocalDateTime localDateTime = DateTimeParser.parse("08/Dec");
        assertEquals(LocalDateTime.of(LocalDateTime.now().getYear(), 12, 8, 12, 0),
                localDateTime);
    }

    /**
     * Tests if parsing D/MMM is correct
     */
    @Test
    public void dateTimeParser_slashDelimiter_dateWordMonthNoYear() {
        LocalDateTime localDateTime = DateTimeParser.parse("8/Dec");
        assertEquals(LocalDateTime.of(LocalDateTime.now().getYear(), 12, 8, 12, 0),
                localDateTime);

        LocalDateTime localDateTime2 = DateTimeParser.parse("23/Dec");
        assertEquals(LocalDateTime.of(LocalDateTime.now().getYear(), 12, 23, 12, 0),
                localDateTime2);
    }

    /**
     * Tests if parsing D MMM YYYY is correct
     */
    @Test
    public void dateTimeParser_spaceDelimiter_dateWordMonthFullYear() {
        LocalDateTime localDateTime = DateTimeParser.parse("8 Dec 2002");
        assertEquals(LocalDateTime.of(2002, 12, 8, 12, 0),
                localDateTime);

        LocalDateTime localDateTime2 = DateTimeParser.parse("23 May 2005");
        assertEquals(LocalDateTime.of(2005, 5, 23, 12, 0),
                localDateTime2);
    }

    /**
     * Tests if parsing D MMM YYYY HH:MM is correct
     */
    @Test
    public void dateTimeParser_spaceDelimiter_dateWordMonthFullYear_withTime() {
        LocalDateTime localDateTime = DateTimeParser.parse("8 Dec 2002 12:08");
        assertEquals(LocalDateTime.of(2002, 12, 8, 12, 8),
                localDateTime);

        LocalDateTime localDateTime2 = DateTimeParser.parse("23 May 2005 8:43");
        assertEquals(LocalDateTime.of(2005, 5, 23, 8, 43),
                localDateTime2);
    }

    /**
     * Tests if parsing D MM is correct
     */
    @Test
    public void dateTimeParser_spaceDelimiter_dateWordMonthNoYear() {
        LocalDateTime localDateTime = DateTimeParser.parse("8 Nov");
        int currentYear = LocalDateTime.now().getYear();
        assertEquals(LocalDateTime.of(currentYear, 11, 8, 12, 0),
                localDateTime);

        LocalDateTime localDateTime2 = DateTimeParser.parse("23 Jul");
        assertEquals(LocalDateTime.of(currentYear, 7, 23, 12, 0),
                localDateTime2);

        LocalDateTime localDateTime3 = DateTimeParser.parse("23 May");
        assertEquals(LocalDateTime.of(currentYear, 5, 23, 12, 0),
                localDateTime3);
    }

    /**
     * Tests if parsing D MM HH:MM is correct
     */
    @Test
    public void dateTimeParser_spaceDelimiter_dateWordMonthNoYear_WithTime() {
        LocalDateTime localDateTime = DateTimeParser.parse("8 Nov 15:40");
        int currentYear = LocalDateTime.now().getYear();
        assertEquals(LocalDateTime.of(currentYear, 11, 8, 15, 40),
                localDateTime);

        LocalDateTime localDateTime2 = DateTimeParser.parse("23 Jul 5:21");
        assertEquals(LocalDateTime.of(currentYear, 7, 23, 5, 21),
                localDateTime2);

        LocalDateTime localDateTime3 = DateTimeParser.parse("23 May 19:11");
        assertEquals(LocalDateTime.of(currentYear, 5, 23, 19, 11),
                localDateTime3);

        LocalDateTime localDateTime4 = DateTimeParser.parse("23 May 19:82");
        assertNull(localDateTime4);
    }

    /**
     * Tests if parsing D MM YYYY  is correct
     */
    @Test
    public void dateTimeParser_spaceDelimiter_dateNumericMonthFullYear() {
        LocalDateTime localDateTime = DateTimeParser.parse("8 11 2002");
        assertEquals(LocalDateTime.of(2002, 11, 8, 12, 0),
                localDateTime);

        LocalDateTime localDateTime2 = DateTimeParser.parse("23 5 2005");
        assertEquals(LocalDateTime.of(2005, 5, 23, 12, 0),
                localDateTime2);

        LocalDateTime localDateTime3 = DateTimeParser.parse("23 05 2005");
        assertEquals(LocalDateTime.of(2005, 5, 23, 12, 0),
                localDateTime3);
    }

    /**
     * Tests if parsing D MM YYYY  is correct
     */
    @Test
    public void dateTimeParser_spaceDelimiter_dateNumericMonthNoYear() {
        LocalDateTime localDateTime = DateTimeParser.parse("8 11");
        int currentYear = LocalDateTime.now().getYear();

        assertEquals(LocalDateTime.of(currentYear, 11, 8, 12, 0),
                localDateTime);

        LocalDateTime localDateTime2 = DateTimeParser.parse("23 5");
        assertEquals(LocalDateTime.of(currentYear, 5, 23, 12, 0),
                localDateTime2);

        LocalDateTime localDateTime3 = DateTimeParser.parse("23 05");
        assertEquals(LocalDateTime.of(currentYear, 5, 23, 12, 0),
                localDateTime3);
    }
    /**
     * Tests if parsing D MM YYYY  is correct
     */
    @Test
    public void dateTimeParser_spaceDelimiter_dateNumericMonthHalfYear() {
        LocalDateTime localDateTime = DateTimeParser.parse("8 11 12");

        assertEquals(LocalDateTime.of(2012, 11, 8, 12, 0),
                localDateTime);

        LocalDateTime localDateTime2 = DateTimeParser.parse("23 5 16");
        assertEquals(LocalDateTime.of(2016, 5, 23, 12, 0),
                localDateTime2);

        LocalDateTime localDateTime3 = DateTimeParser.parse("23 05 19");
        assertEquals(LocalDateTime.of(2019, 5, 23, 12, 0),
                localDateTime3);
    }

}
