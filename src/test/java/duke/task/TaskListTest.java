package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import duke.common.DukeException;
import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    void test() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.getTaskList().size());

        taskList.add(new Todo("hello", false));
        assertEquals(1, taskList.getTaskList().size());
        assertEquals("1) [T][ ] hello\n", taskList.toString());

        try {
            taskList.mark(0, true);
            assertEquals("1) [T][X] hello\n", taskList.toString());
        } catch (Exception e) {
            fail();
        }
        try {
            taskList.delete(0);
            assertEquals(0, taskList.getTaskList().size());
        } catch (Exception e) {
            fail();
        }

        try {
            taskList.mark(0, false);
            fail();
        } catch (DukeException e) {
            assertEquals("Task 1 does not exist!", e.getMessage());
        }
        try {
            taskList.delete(0);
            fail();
        } catch (DukeException e) {
            assertEquals("Task 1 does not exist!", e.getMessage());
        }

        try {
            taskList.add(new Todo("hello", false));
            taskList.add(new Todo("hi", false));
            TaskList filtered = taskList.filter("hello");
            assertEquals(1, filtered.getTaskList().size());
            assertEquals("1) [T][ ] hello\n", filtered.toString());
        } catch (Exception e) {
            fail();
        }
    }
}
