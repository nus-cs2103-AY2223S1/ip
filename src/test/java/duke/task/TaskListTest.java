package duke.task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void add_task_success() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.add(new Todo("item1"));
        Task firstTask = taskList.getTask(0);
        assertEquals(firstTask instanceof Todo, true);
        assertEquals("[T][ ] item1", firstTask.toString());
    }

    @Test
    public void mark_task_success() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.add(new Todo("item1"));
        taskList.mark(0);

        Task firstTask = taskList.getTask(0);
        assertEquals("[T][X] item1", firstTask.toString());
    }
}
