package duke.task;

import duke.constant.PriorityLevel;

/**
 * An abstract class to represent a task.
 *
 * @author Elbert Benedict
 */
public abstract class Task {
    private final String task;
    private boolean isDone;
    private String priority;

    /**
     * Constructs a new Task instance.
     *
     * @param task the task description.
     */
    public Task(String task) {
        this.task = task;
        isDone = false;
        priority = PriorityLevel.NONE;
    }

    /**
     * Constructs a new Task instance.
     *
     * @param task the task description.
     * @param isDone whether the task has been marked as done.
     */
    public Task(String task, boolean isDone, String priority) {
        this.task = task;
        this.isDone = isDone;
        this.priority = priority;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return the status icon of the task.
     */
    public String getStatusIcon() {
        return isDone
                ? "[X]"
                : "[ ]";
    }

    public String getPriority() {
        return priority;
    }

    public String getPriorityIcon() {
        switch (priority) {
        case PriorityLevel.HIGH:
            return "(!!!)";

        case PriorityLevel.MEDIUM:
            return "(!!)";

        case PriorityLevel.LOW:
            return "(!)";

        case PriorityLevel.NONE:
            return "";

        default:
            return "";
        }
    }

    public void modifyPriority(String priority) {
        this.priority = priority;
    }

    /**
     * Returns whether the string s is contained in
     * the task description.
     *
     * @param s the string to be checked.
     * @return whether the string is contained in
     *     the task description.
     */
    public boolean isStringContained(String s) {
        return task.contains(s);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + getPriorityIcon() + " " + task;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task.
     */
    public String getTask() {
        return task;
    }

    public abstract String toSaveFileString();
}
