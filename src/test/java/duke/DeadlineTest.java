package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testStringConversion() {
        Deadline task = new Deadline("homework","2022-12-09");
        assertEquals("[D][ ] homework(by: Dec 09 2022)", task.toString());
    }

    @Test
    public void testChangeFormat() {
        Deadline task = new Deadline("homework","2022-12-09");
        assertEquals("D | 0 | homework | 2022-12-09", task.changeFormat());
    }

    @Test
    public void testMark(){
        Deadline task = new Deadline("homework","2022-12-09");
        task.markAsDone();
        assertEquals("[D][X] homework(by: Dec 09 2022)", task.toString());
    }
}
