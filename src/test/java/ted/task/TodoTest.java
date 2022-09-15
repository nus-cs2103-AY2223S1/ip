package ted.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] quiz", new Todo("quiz").toString());
    }

    @Test
    public void testFileStringConversion() {
        assertEquals("T | 0 | quiz\n",
                new Todo("quiz").toFileString());
    }
}
