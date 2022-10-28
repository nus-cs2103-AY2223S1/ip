package stubs;

import java.util.ArrayList;
import java.util.List;

import henry.Task;
import henry.TaskList;

/**
 * Stub for the TaskList class.
 */
public class TaskListStub extends TaskList {

    private final List<Task> testTasks;

    /**
     * Creates a new TaskListStub with the given input list.
     * @param inputTaskList the list of tasks to be used
     */
    public TaskListStub(List<Task> inputTaskList) {
        super(new ArrayList<>());
        this.testTasks = inputTaskList;
    }

    @Override
    public String addTask(Task task) {
        testTasks.add(task);
        return task.toString();
    }

    @Override
    public Task get(int index) {
        return testTasks.get(index);
    }

    @Override
    public void set(int index, Task task) {
        testTasks.set(index, task);
    }

    @Override
    public int size() {
        return testTasks.size();
    }

    @Override
    public String deleteTask(int index) {
        return testTasks.remove(index).toString();
    }
}
