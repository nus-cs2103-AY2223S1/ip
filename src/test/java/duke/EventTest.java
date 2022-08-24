package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testStringConversion() {
        assertEquals("[E][ ] project meeting (at: Oct 15 2021)", new Event("project meeting", "2021-10-15").toString());
    }
}
