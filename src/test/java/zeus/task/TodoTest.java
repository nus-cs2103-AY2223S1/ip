package zeus.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void todoTest() {
        Todo t = new Todo("test");
        assertEquals("[T][ ] test", t.toString(), "toString() method works");

        t.markAsDone();
        assertEquals("[T][X] test", t.toString(), "markAsDone() method works");

        assertEquals("T | 1 | test", t.getFileFormat(), "getFileFormat() method works");
    }
}
