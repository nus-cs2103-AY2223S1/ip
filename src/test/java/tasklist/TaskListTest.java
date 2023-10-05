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
            assertEquals("[T][X] Testing\n", actual);
        } catch (DukeException e) {
            String actual = e.getMessage();
            assertEquals("[T][X] Testing\n", actual);
        }
    }

    @Test
    public void unmarkTest(){
        try {
            TaskList currTask = new TaskList();
            currTask.addTask(new Todo("Testing"));
            currTask.mark(0);
            String actual = currTask.unmark(0);
            assertEquals("[T][ ] Testing\n", actual);
        } catch (DukeException e) {
            String actual = e.getMessage();
            assertEquals("[T][ ] Testing\n", actual);
        }
    }

    @Test
    public void listTaskTest(){
        try {
            TaskList currTask = new TaskList();
            currTask.addTask(new Todo("Testing"));
            currTask.addTask(new Todo("Testing2"));
            currTask.addTask(new Todo("Testing3"));
            String actual = currTask.listTask();
            assertEquals("1.[T][ ] Testing\n2.[T][ ] Testing2\n3.[T][ ] Testing3\n", actual);
        } catch (DukeException e) {
            String actual = e.getMessage();
            assertEquals("1.[T][ ] Testing\n2.[T][ ] Testing2\n3.[T][ ] Testing3\n", actual);
        }
    }

    @Test
    public void DeleteTest(){
        try {
            TaskList currTask = new TaskList();
            currTask.addTask(new Todo("Testing"));
            currTask.addTask(new Todo("Testing2"));
            currTask.addTask(new Todo("Testing3"));
            String actual = currTask.deleteTask(0);
            assertEquals("[T][ ] Testing\n", actual);
            actual = currTask.listTask();
            assertEquals("1.[T][ ] Testing2\n2.[T][ ] Testing3\n", actual);
        } catch (DukeException e) {
            String actual = e.getMessage();
            assertEquals("1.[T][ ] Testing2\n2.[T][ ] Testing3\n", actual);
        }
    }


}

