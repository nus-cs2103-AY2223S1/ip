package duke;

//@@author chengda300
//Reused from https://nus-cs2103-ay2223s1.github.io/website/schedule/week2/project.html
// with minor modifications

/**
 * Represents a task of any type.
 */
public class Task {
    private boolean isDone;
    private String description;

    /**
     * Constructor for a task.
     *
     * @param desc Task description.
     */
    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    /**
     * Returns the string representation of whether a task is marked as completed.
     *
     * @return "X" if it is marked as completed, " " if not.
     */
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Returns the task description.
     *
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks a task as completed.
     *
     * @return Associated message from Duke.
     */
    public String markTask() {
        this.isDone = true;
        return "Successfully marked this task as done: " + this;
    }

    /**
     * Marks a task as not completed.
     *
     * @return Associated message from Duke.
     */
    public String unmarkTask() {
        this.isDone = false;
        return "Successfully marked this task as not done: " + this;
    }

    /**
     * Returns the string representation of a task.
     *
     * @return String representation of a task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
//@@author
