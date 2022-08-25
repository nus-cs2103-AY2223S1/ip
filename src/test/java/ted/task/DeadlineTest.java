package ted.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] quiz (by: 12 Dec 2022 10:30 AM)",
                new Deadline("quiz", "2022-12-12 10:30").toString());
    }

    @Test
    public void testFileStringConversion() {
        assertEquals("D | 0 | quiz | 2022-12-12T10:30\n",
                new Deadline("quiz", "2022-12-12 10:30").toFileString());
    }
}
