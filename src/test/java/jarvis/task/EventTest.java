package jarvis.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void eventTest() {
        assertEquals("[E][ ] project meeting (at: Aug 31 2022 10:15 PM)", new Event("project meeting", "31/08/2022 2215").toString());
    }
}
