package duke.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.tasks.Task;

public class TaskListTest {
    @Test
    public void addTaskTest() {
        Task task = new TaskStub();
        TaskList taskList = new TaskList();
        taskList.addTask(task);
        assertEquals(taskList.getTask(0), task);
    }

    @Test
    public void deleteTaskTest() {
        IndexOutOfBoundsException thrown = assertThrows(IndexOutOfBoundsException.class, () -> {
            TaskList taskList = new TaskList();
            taskList.deleteTask(0);
        });
        assertEquals("Index 0 out of bounds for length 0", thrown.getMessage());
    }
}
