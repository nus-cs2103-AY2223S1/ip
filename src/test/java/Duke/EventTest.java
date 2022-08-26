package Duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testStringConversion() {
        Event task = new Event("homework","2022-12-09");
        assertEquals("[E][ ] homework(at: Dec 09 2022)", task.toString());
    }

    @Test
    public void testChangeFormat() {
        Event task = new Event("homework","2022-12-09");
        assertEquals("E | 0 | homework | 2022-12-09", task.changeFormat());
    }

    @Test
    public void testMark(){
        Event task = new Event("homework","2022-12-09");
        task.markAsDone();
        assertEquals("[E][X] homework(at: Dec 09 2022)", task.toString());
    }

}
