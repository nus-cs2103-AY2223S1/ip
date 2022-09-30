package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testToStringMethod() {
        assertEquals("[D][ ] deadline task (by: 10 Oct 2022)", new Deadline("deadline task", "2022-10-10").toString());
    }

    @Test
    public void testToMemoryStringMethod() {
        assertEquals("D | 0 | deadline task | 2022-10-10", new Deadline("deadline task", "2022-10-10").toMemoryString());
    }
}
