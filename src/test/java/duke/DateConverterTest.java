package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DateConverterTest {

    @Test
    public void testIsValidDate_valid() {
        assertTrue(DateConverter.isValidDate("2022-08-20"));
    }

    @Test
    public void testIsValidDate_inValid() {
        assertFalse(DateConverter.isValidDate("2022-8-20"));
        assertFalse(DateConverter.isValidDate("20-08-2022"));
        assertFalse(DateConverter.isValidDate("2022-8-8"));
    }
}
