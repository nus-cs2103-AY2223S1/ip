package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] homework", new Todo("homework").toString());
    }
}
