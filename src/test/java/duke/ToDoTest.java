package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void testStringConversion() {
        ToDo t = new ToDo("read book");
        assertEquals("[T][ ] read book", t.toString());
    }

    @Test
    public void testFileStatus() {
        ToDo t = new ToDo("read book");
        t.markAsDone();
        assertEquals("T | 1 | read book", t.fileStatus());
    }
}
