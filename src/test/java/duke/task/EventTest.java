package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testToStringMethod() {
        assertEquals("[E][ ] event task (at: 10 Oct 2022 2.00pm", new Event("event task", "2022-10-10 1400").toString());
    }

    @Test
    public void testToMemoryStringMethod() {
        assertEquals("E | 0 | event task | 2022-10-10 1500", new Event("event task", "2022-10-10 1500").toMemoryString());
    }
}
