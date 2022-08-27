package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testStringConversion() {
        ToDo task = new ToDo("homework");
        assertEquals("[T][ ] homework", task.toString());
    }

    @Test
    public void testChangeFormat() {
        ToDo task = new ToDo("homework");
        assertEquals("T | 0 | homework", task.changeFormat());
    }

    @Test
    public void testMark(){
        ToDo task = new ToDo("homework");
        task.markAsDone();
        assertEquals("[T][X] homework", task.toString());
    }

}
