package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testStringConversion() {
        Deadline deadline = new Deadline("test", "2022-08-24");
        assertEquals("[D][ ] test (by: 24 Aug 2022)", deadline.toString());
    }

    @Test
    public void getType() {
        Deadline deadline = new Deadline("test", "2022-08-24");
        assertEquals("D", deadline.getType());
    }

    @Test
    public void getDate() {
        Deadline deadline = new Deadline("test", "2022-08-24");
        assertEquals("2022-08-24", deadline.getDate());
    }
}
