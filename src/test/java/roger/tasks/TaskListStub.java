package roger.tasks;

import java.util.List;

/**
 * Testing stub for TaskList class.
 */
public class TaskListStub extends TaskList {
    public TaskListStub() {
    }

    public TaskListStub(List<Task> tasks) {
    }

    public void add(Task task) {
    }

    public Task delete(int taskNum) {
        return new TaskStub("hello");
    }
}
