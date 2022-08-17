package data;

import data.tasks.Task;

public class TaskList {

    private final Task[] tasks;
    private int currentTaskIdx;

    public TaskList() {
        this.tasks = new Task[100];
        this.currentTaskIdx = 0;
    }

    public int size() {
        return currentTaskIdx;
    }

    public boolean isEmpty() {
        return currentTaskIdx == 0;
    }

    public Task getTask(int taskIdx) {
        return tasks[taskIdx];
    }

    public void addTask(Task task) {
        tasks[currentTaskIdx] = task;
        currentTaskIdx++;
    }
}
