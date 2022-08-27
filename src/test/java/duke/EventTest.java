package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventTest {
    @Test
    public void testCreateEvent() {
        Event event = new Event("CS2103T iP lecture", "on 2022-08-26");
        assertEquals(event.toString(), "[E][ ] CS2103T iP lecture (on 2022-08-26)");
    }

    @Test
    public void testMarkevent() {
        Event event = new Event("CS2103T iP lecture", "on 2022-08-26");
        event.markDone();
        assertEquals(event.toString(), "[E][X] CS2103T iP lecture (on 2022-08-26)");
    }

    @Test
    public void testLoadEvent() {
        Event event = Event.fromSaveString("E,0,\"CS2103T iP lecture\",\"2022-08-26\"");
        assertEquals(event.toString(), "[E][ ] CS2103T iP lecture (on 2022-08-26)");
    }
    
    @Test
    public void testLoadInvalidEvent1() {
        assertThrows(DukeException.class, () -> Event.fromSaveString("E,0,asda"));
    }
    
    @Test
    public void testLoadInvalidEvent2() {
        assertThrows(DukeException.class, () -> Event.fromSaveString(""));
    }
}
