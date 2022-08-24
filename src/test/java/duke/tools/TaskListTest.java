package duke.tools;

import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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