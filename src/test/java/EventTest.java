import mia.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    private final String DUMMY_EVENT = "23 January 2022 08:00;;23 January 2022 08:00;;0;;Title;;";

    @Test
    public void saveFormatTest() {
        final Event event = Event.fromSaveFormat(DUMMY_EVENT);
        assertEquals("E;;" + DUMMY_EVENT, event.toSaveFormat());
    }

    @Test
    public void stringTest() {
        final Event event = Event.fromSaveFormat(DUMMY_EVENT);
        assertEquals("📆 ☐ Title (from 2022-01-23 08:00 to 2022-01-23 08:00)", event.toString());
    }
}
