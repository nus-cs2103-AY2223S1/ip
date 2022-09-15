package tasklist;

import duke.exception.DukeException;
import duke.tasklist.TaskList;
import org.junit.jupiter.api.Test;
import duke.task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void markTest(){
        try {
            TaskList currTask = new TaskList();
            currTask.addTask(new Todo("Testing"));
            String actual = currTask.mark(0);
            assertEquals("\t    [T][X] Testing\n", actual);
        } catch (DukeException e) {

        }

    }

    @Test
    public void unmarkTest(){
        try {
            TaskList currTask = new TaskList();
            currTask.addTask(new Todo("Testing"));
            currTask.mark(0);
            String actual = currTask.unmark(0);
            assertEquals("\t    [T][ ] Testing\n", actual);
        } catch (DukeException e) {

        }



    }
}

