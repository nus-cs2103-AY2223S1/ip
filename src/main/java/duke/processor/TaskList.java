package duke.processor;

import java.util.ArrayList;

import duke.task.Task;

public class TaskList {
    protected ArrayList<Task> tasks;
    protected int numberOfTasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.numberOfTasks = 0;
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.numberOfTasks = tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int index) {
        tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }
}
