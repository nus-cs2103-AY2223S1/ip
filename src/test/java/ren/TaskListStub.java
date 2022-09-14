package ren;

/**
 * Stub for TaskList.
 */
public class TaskListStub extends TaskList {
    /**
     * Constructor for a TaskListStub.
     */
    public TaskListStub() {
        super(new Storage("data/list_test.txt"));
    }

    @Override
    public String addTask(Ren.TaskType type, String task, String dateTime) {
        return "";
    }

    @Override
    public String deleteTask(int taskNum) {
        return "";
    }

    @Override
    public String updateTask(boolean status, int taskNum) {
        return "";
    }

    @Override
    public String listTasks() {
        return "";
    }

    @Override
    public String findTasks(String term) {
        return "";
    }

    @Override
    public String emptyList() {
        return "";
    }
}
