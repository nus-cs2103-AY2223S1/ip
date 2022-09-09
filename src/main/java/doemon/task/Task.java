package doemon.task;

import java.util.ArrayList;

public class Task {
    /**
     * The description of the task.
     */
    private String description;
    /**
     * A boolean indicating if the task is marked as done.
     */
    private boolean isDone;

    /**
     * Constructor for a Task object.
     * @param description the string description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Checks if the specified keyword appears in the task description.
     *
     * @param keyword String to be checked.
     * @return Boolean indicating if the keyword was found in the task description.
     */
    public boolean matchesDescription(String keyword) {
        // Remove task and mark label
        String taskString = this.toString().substring(7);
        if (taskString.toLowerCase().contains(keyword.toLowerCase())) {
            return true;
        }
        return false;
    }

    /**
     * Returns a string used to save the task.
     * @return a string used to save the task
     */
    public String saveString() {
        String isMarked = isDone ? "1" : "0";
        return String.format("%s | %s", isMarked, this.description);
    }

    /**
     * Returns a string representation of this task.
     * @return a string representing this task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.description);
    }
}
