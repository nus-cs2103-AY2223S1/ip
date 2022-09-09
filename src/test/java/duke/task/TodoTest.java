package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void printingTest() {
        Todo task = new Todo("buy bread");
        assertEquals("[T][ ] buy bread", task.toString());
    }

    @Test
    public void getTaskTypeTest() {
        Todo task = new Todo("");
        assertEquals("T", task.getTaskType());
    }
}
