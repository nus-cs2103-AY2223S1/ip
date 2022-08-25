package duke;

import duke.task.Event;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit test for event.
 */
public class EventTest {

    /**
     * Tests whether event is formatted correctly.
     */
    @Test
    public void event_createEvent_correctStringFormat() {
        String time = "2019-10-15";
        LocalDate date = LocalDate.parse(time);
        Event event = new Event("Run event test 1 ", date);
        assertEquals("[E][ ] Run event test 1 (at: Oct 15 2019)", event.toString());
    }

    /**
     * Tests whether event is of correct type.
     */
    @Test
    public void event_createEvent_correctType() {
        String time = "2019-10-15";
        LocalDate date = LocalDate.parse(time);
        Event event = new Event("Run event test 1 ", date);
        assertEquals("E", event.getType());
    }
}
