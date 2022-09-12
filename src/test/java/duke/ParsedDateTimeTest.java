package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class ParsedDateTimeTest {
    @Test
    @Timeout(1)
    public void of_makingObjects_normalBehavior() {
        assertEquals(ParsedDateTime.of("345", true).toString(), "31 Dec 0345 2359");
        assertEquals(ParsedDateTime.of("345", false).toString(), "01 Jan 0345 0000");
        assertEquals(ParsedDateTime.of("29/2/1984", false).toString(), "29 Feb 1984 0000");
        assertEquals(ParsedDateTime.of("29-2-1984", true).toString(), "29 Feb 1984 2359");
        assertEquals(ParsedDateTime.of("Friday 13th 1985", true).toString(), "13 Sep 1985 2359");
        assertEquals(ParsedDateTime.of("Friday 13th 1985", false).toString(), "13 Sep 1985 0000");
        assertEquals(ParsedDateTime.of("January 1984", true).toString(), "31 Jan 1984 2359");
        assertEquals(ParsedDateTime.of("Jan 1984", false).toString(), "01 Jan 1984 0000");
        assertEquals(ParsedDateTime.of("Friday MAY 1984", true).toString(), "04 May 1984 2359");
        assertEquals(ParsedDateTime.of("Friday MAY 1984", false).toString(), "04 May 1984 0000");
        assertEquals(ParsedDateTime.of("12:34", true).toString(),
                ParsedDateTime.of("12:34", false).toString());
        assertEquals(ParsedDateTime.of("12:34pm", true).toString(),
                ParsedDateTime.of("12:34", false).toString());
        assertEquals(ParsedDateTime.of("11:34am", true).toString(),
                ParsedDateTime.of("11:34", false).toString());
        assertEquals(ParsedDateTime.of("11:34pm", true).toString(),
                ParsedDateTime.of("23:34", false).toString());
        assertEquals(ParsedDateTime.of("12:34am", true).toString(),
                ParsedDateTime.of("00:34", false).toString());
        assertEquals(ParsedDateTime.of("12:34Am", true).toString(),
                ParsedDateTime.of("12:34aM", false).toString());
        assertEquals(ParsedDateTime.of("1st 1th 1St 1/1/1950 January Jan jan 1950", true).toString(),
                "01 Jan 1950 2359");
    }

    @Test
    @Timeout(1)
    public void of_makingObjects_cannotConstruct() {
        String[] testCases = new String[]{
            "2022 fri 31st", "29/2 2100", "29-2-2100", "12/12-12", "23rd 14/5", "34/56", "-3",
            "Jan Jul", "2030 2040", "Monday Tuesday", "1st 2nd",
            "13.00am", "0.00am", "13.0pm", "0.0pm", "-3:-3", "26.26"
        };
        for (String s : testCases) {
            assertEquals(ParsedDateTime.of(s, true).toString(), s);
            assertEquals(ParsedDateTime.of(s, false).toString(), s);
        }
    }
}
