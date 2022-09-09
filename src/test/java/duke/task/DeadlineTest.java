package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] something (by: Jan 01 2000)",
                new Deadline("something", "2000-01-01").toString());
    }
}
