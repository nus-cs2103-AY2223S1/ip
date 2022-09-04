package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Defines unit test for <Code>Event</Code> class.
 */
public class EventTest {
    /** Dummy event used for testing. */
    private static final Event dummyEvent = new Event(
            "EVENT DESCRIPTION", "EVENT VENUE", Level.LOW);

    /**
     * Tests <Code>toString</Code> method of <Code>Event</Code> class.
     */
    @Test
    public void testStringConversion() {
        String expectedStringFormat =
                "[E][ ] EVENT DESCRIPTION (at: EVENT VENUE)";
        assertEquals(expectedStringFormat, dummyEvent.toString());
    }

    /**
     * Tests <Code>toFileFormat</Code> method of <Code>Event</Code> class.
     */
    @Test
    public void testFileFormatConversion() {
        String expectedFileFormat =
                "event|false|EVENT DESCRIPTION|EVENT VENUE";
        assertEquals(expectedFileFormat, dummyEvent.toFileFormat());
    }
}
