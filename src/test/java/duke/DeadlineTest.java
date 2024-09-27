package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testStringConversion() {
        Deadline deadline = new Deadline("deadline", false, "01-02-2022");
        assertEquals("[D][ ] deadline (by: 1 Feb 2022)", deadline.toString());
    }
}
