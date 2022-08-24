package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void testMark() {
        Task t = new Task("todo borrow book");
        assertEquals(" ",t.getStatusIcon());
        t.taskDone();
        assertEquals("X",t.getStatusIcon());
        t.taskUndone();
        assertEquals(" ",t.getStatusIcon());
    }
}
