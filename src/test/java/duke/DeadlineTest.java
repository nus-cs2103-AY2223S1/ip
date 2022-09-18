package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] test deadline(Aug 23 2022)", new Deadline("test deadline", "2022-08-23").toString());
    }
}
