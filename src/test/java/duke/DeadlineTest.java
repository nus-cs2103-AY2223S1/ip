package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toStringTest() {
        assertEquals("[D][ ] homework (by Oct 10 2010 2359)", new Deadline(
                "homework", "2010-10-10 2359").toString());
    }
}
