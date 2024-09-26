package iana.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void toStringTest() {
        Event event = new Event("event pool party", "9 Dec, 10pm", false);
        assertEquals("[E][] event pool party (at: 9 Dec 10pm)", event.toString());
    }
}
