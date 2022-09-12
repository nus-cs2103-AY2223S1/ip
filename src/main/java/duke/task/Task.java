package duke.task;

import duke.Command;

/**
 * An abstract class representing the {@code Task}
 */
public abstract class Task {
    /**
     * The description of the {@code Task}
     */
    protected final String description;
    /**
     * The {@code Command} type of the {@code Task}
     */
    protected final Command taskCommand;
    private boolean isDone;

    /**
     * Constructs the {@code Task}
     * @param description The description of the {@code Task}
     * @param taskCommand The {@code Command} type of the {@code Task}
     */
    public Task(String description, Command taskCommand) {
        this.description = description;
        isDone = false;
        this.taskCommand = taskCommand;
    }

    /**
     * Returns the status icon if the task is done
     * @return A {@code String} representation of the icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void setTaskAsDone() {
        isDone = true;
    }

    /**
     * Marks as the task as not done.
     */
    public void setTaskAsNotDone() {
        isDone = false;
    }

    /**
     * Returns a string representation of the mark command
     * @param index The index of the task
     * @return A {@code String} representing the mark command, if the task is marked done
     */
    protected String getTaskDoneString(int index) {
        return isDone ? "mark " + index + "\n" : "";
    }

    /**
     * Returns a string representation of the command for storage
     * @param index The index of the task
     * @return A {@code String} representing the command
     */
    public abstract String getFileStorageString(int index);

    /**
     * Checks if the keyword is in the task description
     * @param keyword The user's inputted keyword
     * @return True if the keyword is present
     */
    public boolean isMatchingKeywordInDescription(String keyword) {
        return description.contains(keyword);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
