package duke.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateParserTest {
    @Test
    public void isDateValidTest() {
        assertEquals(DateParser.isDateValid("2022-08-26"), true);
        assertEquals(DateParser.isDateValid("26-08-2022"), false);
        assertEquals(DateParser.isDateValid("Aug 26 2022"), false);
        assertEquals(DateParser.isDateValid("08-26-2022"), false);
        assertEquals(DateParser.isDateValid("2022-13-30"), false);
    }
}
