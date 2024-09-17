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
    public void task_findTask_taskFoundCorrectly() {
        Todo testTodo = new Todo("Dummy Name", false);
        boolean isTaskFound = testTodo.doesNameContain("my N");
        assertEquals(isTaskFound, true);
    }

    @Test
    public void task_findTask_taskNotFoundCorrectly() {
        Todo testTodo = new Todo("Dummy Name", false);
        boolean isTaskFound = testTodo.doesNameContain("myN");
        assertEquals(isTaskFound, false);
    }

    @Test
    public void task_markTask_isMarked() {
        Todo testTodo1 = new Todo("Dummy Name 1", false);
        testTodo1.mark();
        assertTrue(testTodo1.getStatus() == true);
    }
}
