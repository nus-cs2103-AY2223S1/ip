package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Defines unit test for {@code Event} class.
 */
public class EventTest {
    /** Dummy event used for testing. */
    private static final Event dummyEvent = new Event(
            "EVENT DESCRIPTION", "EVENT VENUE", Level.LOW);

    /**
     * Tests {@code toString} method of {@code Event} class.
     */
    @Test
    public void testStringConversion() {
        String expectedStringFormat =
                "[E][ ][LOW] EVENT DESCRIPTION (at: EVENT VENUE)";
        assertEquals(expectedStringFormat, dummyEvent.toString());
    }

    /**
     * Tests {@code toFileFormat} method of {@code Event} class.
     */
    @Test
    public void testFileFormatConversion() {
        String expectedFileFormat =
                "event|false|EVENT DESCRIPTION|LOW|EVENT VENUE";
        assertEquals(expectedFileFormat, dummyEvent.toFileFormat());
    }
}
