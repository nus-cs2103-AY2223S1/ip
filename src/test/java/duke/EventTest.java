package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testStringConversion() {
        assertEquals("[E][ ] project meeting (at: Oct 15 2021)", new Event("project meeting", "2021-10-15").toString());
    }
}
