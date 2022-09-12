package iana.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toStringTest() {
        Event event = new Event("event pool party", "9 Dec, 10pm", false);
        assertEquals("[E][] event pool party (at: 9 Dec 10pm)", event.toString());
    }
}
