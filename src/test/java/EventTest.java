import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Event;

/**
 * To unit test the Event class.
 */
public class EventTest {

    /**
     * Tests the creation of an Event object.
     */
    @Test
    public void testCreateEvent() {
        Event event = new Event("test", "tmr");
        String result = "[E][O] test (at: tmr)";
        assertEquals(result, event.toString());
    }

    /**
     * Tests the save format of an Event object.
     */
    @Test
    public void testFormatting() {
        Event event = new Event("test", "tmr");
        String result = "E | O | test | tmr\n";
        assertEquals(result, event.formatFileText());
    }

}
