package tako;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import tako.task.Task;

public class TaskListTest {
    @Test
    public void mark_validTaskNumber_success() throws TakoException {
        TaskList tasks = new TaskList();
        tasks.add(new Task("sleep"));
        tasks.mark(0);
        assertEquals("[X] sleep", tasks.get(0).toString());
    }

    @Test
    public void remove_validTaskNumber_success() throws TakoException {
        TaskList tasks = new TaskList();
        Task task = new Task("sleep");
        tasks.add(task);
        assertEquals(task, tasks.remove(0));
        assertEquals(0, tasks.getSize());
    }

    @Test
    public void mark_invalidTaskNumber_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            tasks.mark(0);
            fail();
        } catch (TakoException e) {
            assertEquals("The task number to mark does not exist.", e.getMessage());
        }
    }

    @Test
    public void remove_invalidTaskNumber_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            tasks.remove(0);
            fail();
        } catch (TakoException e) {
            assertEquals("The task number to delete does not exist.", e.getMessage());
        }
    }
}
