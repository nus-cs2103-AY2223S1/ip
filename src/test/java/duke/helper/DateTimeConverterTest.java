package duke.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

public class DateTimeConverterTest {

    DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("uuuu-M-d");
    DateTimeConverter converter = new DateTimeConverter(formatter);

    @Test
    public void convertTest() {
        String[] dateTime = new String[] {"2022-5-12", "1800"};

        assertEquals(converter.convert(dateTime),
                "May 12 2022 6:00 PM");
    }

    @Test
    public void isValidDateTest() {
        assertEquals(converter.isValidDate("2022-12-12"), true);
        assertEquals(converter.isValidDate("2022-13-12"), false);
        assertEquals(converter.isValidDate("2022-12-32"), false);
    }
}
