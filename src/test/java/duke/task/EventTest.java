package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testGetTask() {
        Event e = new Event("complete iP /at 19-09-2022");
        assertEquals("[E][ ]complete iP (at: 19 Sep 2022)", e.getTask());
    }
}
