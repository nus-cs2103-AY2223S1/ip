package maria.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeDateTimeFormatterTest {

    @Test
    public void formatDisplayTest() {

        assertEquals(
                DukeDateTimeFormatter.formatDisplay(LocalDate.of(2022, 8, 8)),
                "8 August 2022"
        );

    }

}
