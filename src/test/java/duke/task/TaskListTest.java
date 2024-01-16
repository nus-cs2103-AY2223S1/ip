package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;

public class TaskListTest {
    @Test
    public void add_task_success() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.add(new Todo("item1"));
        try {
            Task firstTask = taskList.getTask(0);
            assertEquals(firstTask instanceof Todo, true);
            assertEquals("[T][ ] item1", firstTask.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void mark_task_success() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.add(new Todo("item1"));
        try {
            taskList.mark(0);
            Task firstTask = taskList.getTask(0);
            assertEquals("[T][X] item1", firstTask.toString());
        } catch (DukeException e) {
            fail();
        }
    }
}
