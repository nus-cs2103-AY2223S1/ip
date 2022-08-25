package duke;

import duke.task.Todo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toStringTest() {
        assertEquals("[T][ ] todo", new Todo("todo").toString());
    }

    @Test
    public void getSaveFormatTest() {
        assertEquals("T | 0 | todo", new Todo("todo").getSaveFormat());
    }
}
