package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testStringConversion() {
        assertEquals("[E][ ] test event(Aug 23 2022)", new Event("test event", "2022-08-23").toString());
    }
}
