package task;

import utils.Prompt;

/**
 * The {@code Task} stores relevant information for a task.
 * It contains the {@link Task#taskName task name}, and a {@link Task#done boolean} to indicate if
 * it is done or not.
 */
public class Task {

    final private String taskName;
    private boolean done;

    /**
     * Constructor for a task deadline. All task and undone from the start.
     *
     * @param taskName a string representing the name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Marks a task as done.
     */
    public void markDone() {
        this.done = true;
        Prompt.markDone(this.taskName);
    }

    /**
     * Marks a task as undone.
     */
    public void markUndone() {
        this.done = false;
        Prompt.markUndone(this.taskName);
    }

    /**
     * Returns string representation of a task.
     *
     * @return a string representing the task.
     */
    @Override
    public String toString() {
        String checkbox = this.done ? "[X] " : "[ ] ";
        return checkbox + this.taskName;
    }
}