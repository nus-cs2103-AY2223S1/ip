package jude;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.LocalDateTime;

// Test template adapted from https://se-education.org/guides/tutorials/junit.html
import org.junit.jupiter.api.Test;

import jude.util.DateUtils;

/**
 * Tests functionality of Deadline class.
 */
public class DateUtilsTest {

    /**
     * Test Case 1: Tests interval between dates, from earlier than to.
     *
     * @throws IOException When system I/O fails.
     */
    @Test
    public void testIntervalBetweenDates_fromBeforeTo() throws IOException {
        long result = DateUtils.calculateTimeDifference(LocalDateTime.of(2022, 9, 8, 16, 0, 0),
                LocalDateTime.of(2022, 9, 8, 16, 0, 5));
        assertEquals(result, 5);
    }

    /**
     * Test Case 1: Tests interval between dates, from after to.
     *
     * @throws IOException When system I/O fails.
     */
    @Test
    public void testIntervalBetweenDates_fromAfterTo() throws IOException {
        long result = DateUtils.calculateTimeDifference(LocalDateTime.of(2022, 9, 8, 16, 0, 15),
                LocalDateTime.of(2022, 9, 8, 16, 0, 5));
        assertEquals(result, -10);
    }
}
