package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void toStringTest() {
        assertEquals("[T][ ] Test message", (new Todo("Test message")).toString());
        Todo todo2 = new Todo("Test marked message");
        todo2.markAsDone();
        assertEquals("[T][X] Test marked message", todo2.toString());
    }

    @Test
    public void toStringForFileTest() {
        assertEquals("N|todo test message", (new Todo("test message")).toStringForFile());
    }
}
