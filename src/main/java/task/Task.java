package task;

import utils.Prompt;

public class Task {
    final private String taskName;
    private boolean done;

    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public void markDone() {
        this.done = true;
        Prompt.markDone(taskName);
    }

    public void markUndone() {
        this.done = false;
        Prompt.markUndone(taskName);
    }

    @Override
    public String toString() {
        String checkbox = this.done ? "[X] " : "[ ] ";
        return checkbox + this.taskName;
    }
}