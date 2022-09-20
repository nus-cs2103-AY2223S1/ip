package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todo_sampleTask() {
        Task todoTask = new Todo("Test");
        assertEquals(todoTask.toString(), "[T][ ] Test");
    }
}
