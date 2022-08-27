package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void dbRepresentationFromUserInput() {
        Task event = new Event("project meeting", "24/04/2019 1600");
        assertEquals("E|false|project meeting|2019-04-24T16:00", event.dbRepresentation());
    }

    @Test
    public void dbRepresentationFromDbRead() {
        Task event = new Event(false, "project meeting", "2019-04-24T16:00");
        assertEquals("[E][ ] project meeting(at: 24/4/2019 1600)", event.toString());
    }
}
