package duke;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void getTest() {
        Task task = new Task("do iP");
        task.mark();
        assertEquals("[X]", task.getStatusIcon());
        task.unmark();
        assertEquals("[ ]", task.getStatusIcon());
    }
}
