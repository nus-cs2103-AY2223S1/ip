package duke;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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
