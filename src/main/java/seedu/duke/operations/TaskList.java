package seedu.duke.operations;

import seedu.duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> tasks) {
        this.taskList = new ArrayList<>(tasks);
    }

    public int numOfTasks() {
        return taskList.size();
    }

    public Task fetchTask(int taskIndex) {
        return taskList.get(taskIndex - 1);
    }

    public boolean isEmpty() {
        return numOfTasks() == 0;
    }

    public Task removeTask(int index) {
        return taskList.remove(index);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }
}
