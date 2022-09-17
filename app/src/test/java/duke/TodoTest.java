package duke;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TodoTest {
    @Test
    public void getTaskIcon_shouldBeT() {
        Todo todo = new Todo("hello");
        assertEquals("Task icon should be a T", todo.getTaskIcon(), "T");
    }
}
