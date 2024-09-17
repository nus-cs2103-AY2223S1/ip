import duke.Event;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void eventString() {
        Event e = new Event("meeting", "2022-06-22");
        assertEquals("[E][ ] meeting (at: Jun 22 2022)", e.toString());
    }
}
