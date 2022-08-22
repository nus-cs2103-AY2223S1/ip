package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for `Event` class.
 */
public class EventTest {
    /** Dummy event used for testing. */
    private static final Event dummyEvent = new Event(
            "EVENT DESCRIPTION", "EVENT VENUE");

    /**
     * Tests `toString` method of `Event` class.
     */
    @Test
    public void testStringConversion() {
        String expectedStringFormat =
                "[E][ ] EVENT DESCRIPTION (at: EVENT VENUE)";
        assertEquals(expectedStringFormat, dummyEvent.toString());
    }

    /**
     * Tests `toFileFormat` method of `Event` class.
     */
    @Test
    public void testFileFormatConversion() {
        String expectedFileFormat =
                "event|false|EVENT DESCRIPTION|EVENT VENUE";
        assertEquals(expectedFileFormat, dummyEvent.toFileFormat());
    }
}
