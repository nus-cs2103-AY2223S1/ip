package duke;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TodoTest {
    @Test
    public void getTaskIcon_shouldBeT() {
        Todo todo = new Todo("hello");
        assertEquals("Task icon should be a T", todo.getTaskIcon(), "T");
    }
}
