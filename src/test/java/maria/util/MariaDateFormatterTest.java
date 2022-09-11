package maria.util;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MariaDateFormatterTest {

    @Test
    public void formatDisplayTest() {

        assertEquals(
                MariaDateFormatter.formatDisplay(LocalDate.of(2022, 8, 8)),
                "8 August 2022"
        );

    }

}
