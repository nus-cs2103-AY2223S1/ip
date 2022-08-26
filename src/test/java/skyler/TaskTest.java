package skyler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void markAsDoneTest() {
        Task todo = new Todo("CS2103 iP");
        todo.markAsDone();
        assertEquals("X", todo.getStatusIcon());
    }
}
