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
    void test_markTask() {
        t.markTask();
        assertEquals("X", t.getStatusIcon());
    }

    @Test
    void test_unmarkTask() {
        t.unmarkTask();
        assertEquals(" ", t.getStatusIcon());
    }
}
