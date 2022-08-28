package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void newEventTest() {
        Event event = new Event("Test description", "09/02/2022 0600");
        assertEquals(event.toString(), "[E][ ] Test description (at: 09 02 2022 06:00)");
    }

    @Test
    public void markEventTest() {
        Event event = new Event("Test description", "09/02/2022 0600");
        event.mark();
        assertEquals(event.toString(), "[E][X] Test description (at: 09 02 2022 06:00)");
    }

}
