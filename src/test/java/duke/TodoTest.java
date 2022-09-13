package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] testing", new ToDo("testing").toString());
    }

    @Test
    public void testStorageStringConversion() {
        assertEquals("T | 0 | testing", new ToDo("testing").toStorageString());
    }

}
