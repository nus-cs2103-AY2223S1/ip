package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventTest {
    private static final String EVENT_DESCRIPTION = "project meeting";
    private static final LocalDateTime EVENT_TIMING = LocalDateTime.parse("2022-09-10 11:11", Task.DATE_TIME_FORMATTER);
    private static final Event EVENT = new Event(EVENT_DESCRIPTION, EVENT_TIMING);

    @Test
    public void encode_eventObject_success() {
        String expectedOutput = "E | 0 | " + EVENT_DESCRIPTION + " | 2022-09-10T11:11";

        assertEquals(expectedOutput, EVENT.encode());
    }

    @Test
    public void testStringConversion() {
        String expectedOutput = "[E][ ] " + EVENT_DESCRIPTION + " (at: 10-Sep-22 11:11)";
        assertEquals(expectedOutput, EVENT.toString());
    }
}
