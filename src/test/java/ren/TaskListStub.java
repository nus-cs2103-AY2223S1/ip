package ren;

public class TaskListStub extends TaskList {
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
    public String emptyList() {
        return "";
    }
}
