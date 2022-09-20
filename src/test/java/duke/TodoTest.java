package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void toStringTest() {
        assertEquals("1.[T][ ] test", new Todo("test").toString());
    }

    @Test
    public void markTest() {
        Task task = new Todo("test");
        task.setStatus(true);
        assertEquals("1.[T][X] test", task.toString());
    }
}
