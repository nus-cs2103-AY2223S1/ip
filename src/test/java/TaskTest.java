import duke.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {
    Todo t = new Todo("123");

    @Test
    void testGetStatusIcon() {
        assertEquals(" ", t.getStatusIcon());
    }

    @Test
    void testMarkTask() {
        t.markTask();
        assertEquals("X", t.getStatusIcon());
    }

    @Test
    void testUnmarkTask() {
        t.unmarkTask();
        assertEquals(" ", t.getStatusIcon());
    }
}