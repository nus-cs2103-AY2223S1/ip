package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EventTest {

    @Test
    void testToString() {
        assertEquals("[E][ ] project meeting (at: Aug 6th 2-4pm)",
                new Event("project meeting", "Aug 6th 2-4pm").toString());
    }
}
