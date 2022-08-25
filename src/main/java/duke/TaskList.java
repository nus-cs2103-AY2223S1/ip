package duke;

import task.Task;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> TASKS;

    public TaskList() {
        TASKS = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        TASKS = taskList;
    }

    public ArrayList<Task> getTasks() {
        return this.TASKS;
    }

    public void addTask(Task task) {
        TASKS.add(task);
    }

    public Task removeTask(int index) {
        return TASKS.remove(index);
    }

    public void markTask(int index) {
        TASKS.get(index).toggleDoneness();
    }

    public void unmarkTask(int index) {
        TASKS.get(index).toggleDoneness();
    }

    public int size() {
        return TASKS.size();
    }
}
