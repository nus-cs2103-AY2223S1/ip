package duke;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toStringTest() {
        assertEquals("[D][ ] Deadline (by: Dec 16 2022)", new Deadline("Deadline", "2022-12-16").toString());
    }

    @Test
    public void deadlineTest() {
        assertEquals("Sep 16 2022", new Deadline("Deadline", "2022-09-16").deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
