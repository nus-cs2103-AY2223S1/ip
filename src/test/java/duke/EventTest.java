package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void timeTest() throws DukeException {
        assertEquals("Dec 31 2022", new Event("Event", "2022-12-31").time
                .format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Test
    public void toStringTest() throws DukeException {
        assertEquals("[E][ ] Event (at: Dec 31 2022)",
                new Event("Event", "2022-12-31").toString());
    }

    @Test
    public void toDataTest() throws DukeException {
        assertEquals("E,  , Event, 2022-12-31", new Event("Event", "2022-12-31").toData());
    }
}
