package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {
    @Test
    public void dummyTest() {
        assertEquals(1, 1);
    }

    @Test
    public void task_compareTaskAndTodo_notEquals() {
        Todo testTodo = new Todo("Dummy Name");
        Task testTask = new Task("Dummy Name");
        assertNotEquals(testTodo, testTask);
    }

    @Test
    public void task_markTask_isMarked() {
        Todo testTodo1 = new Todo("Dummy Name 1");
        testTodo1.mark();
        assertTrue(testTodo1.getStatus() == true);
    }
}
