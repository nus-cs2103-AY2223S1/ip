package duke.tasks;

import java.time.LocalDateTime;

/**
 * Describes the abstract class task.
 * Abstracts out the commonalities of the tasks.
 */
public abstract class Task implements Comparable<Task> {
    protected final String description;
    protected boolean isMarked;
    protected LocalDateTime time = LocalDateTime.MAX;

    /**
     * Describes the constructor when taking user input.
     * @param description description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    /**
     * Describes the constructor when reading from database.
     * @param isMarked boolean value of whether the task is marked.
     * @param description description of the task.
     */
    public Task(boolean isMarked, String description) {
        this.isMarked = isMarked;
        this.description = description;
    }

    public void mark(boolean isCompleted) {
        this.isMarked = isCompleted;
    }

    public abstract String dbRepresentation();

    private String getStatusIcon() {
        return this.isMarked ? "X" : " ";
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    @Override
    public int compareTo(Task task) {
        return this.time.compareTo(task.time);
    }
}
