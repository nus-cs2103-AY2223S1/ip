package tasklist;

import org.junit.jupiter.api.Test;
import task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void markTest(){
        TaskList currTask = new TaskList();
        currTask.addTask(new Todo("Testing"));
        String actual = currTask.mark(0);
        assertEquals("\t    [T][X] Testing\n", actual);
    }

    @Test
    public void unmarkTest(){
        TaskList currTask = new TaskList();
        currTask.addTask(new Todo("Testing"));
        currTask.mark(0);
        String actual = currTask.unmark(0);

        assertEquals("\t    [T][ ] Testing\n", actual);
    }
}

