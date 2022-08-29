package duke.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.tasks.Task;

class TaskListTest {

    private static class DummyTask extends Task {
        public DummyTask() {
            super("dummy", TaskType.TODO);
        }
    }

    @Test
    void getStoredTasksTest() {
        List<Task> storedTasks = new ArrayList<>();
        storedTasks.add(new DummyTask());
        storedTasks.add(new DummyTask());
        storedTasks.add(new DummyTask());
        TaskList taskList = new TaskList(storedTasks);
        assertEquals(storedTasks, taskList.getStoredTasks());
    }

    @Test
    void getSize() {
        List<Task> storedTasks = new ArrayList<>();
        storedTasks.add(new DummyTask());
        storedTasks.add(new DummyTask());
        storedTasks.add(new DummyTask());
        TaskList taskList = new TaskList(storedTasks);
        assertEquals(3, taskList.getSize());
    }

    @Test
    void getTask() {
    }

    @Test
    void setTaskDoneStatus() {
    }

    @Test
    void addTask() {
    }

    @Test
    void deleteTask() {
    }
}
