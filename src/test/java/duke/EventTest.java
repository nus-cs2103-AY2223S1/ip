package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toStringTest() {
        assertEquals("[E][] outing (at: 2-4pm)",
                new Event("outing",false, "2-4pm").toString());
    }
}
