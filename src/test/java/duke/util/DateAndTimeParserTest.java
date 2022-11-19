package duke.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DateAndTimeParserTest {
    @Test
    public void convertDateTest() {
        LocalDate dateInput = LocalDate.of(2022, 8, 28);
        String dateOutput = "Aug 28 2022";
        assertEquals(dateOutput, DateAndTimeParser.convertDate(dateInput));
    }

    @Test
    public void validateAndParseTest() {
        String unrecognisedDateFormat = "August 28th, 2022";
        String recognisedDateFormat = "2022-08-28";
        LocalDate dateOutput = LocalDate.of(2022, 8, 28);

        assertNull(DateAndTimeParser.validateAndParse(unrecognisedDateFormat));
        assertEquals(dateOutput, DateAndTimeParser.validateAndParse(recognisedDateFormat));

    }
}
