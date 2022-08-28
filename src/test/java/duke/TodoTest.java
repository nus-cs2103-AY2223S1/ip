package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void toStringTest() {
        assertEquals("[T][ ] homework", new ToDo("homework").toString());
    }
    @Test
    public void toLineTest() {
        assertEquals("T*0*homework", new ToDo("homework").toLine());
    }
}
