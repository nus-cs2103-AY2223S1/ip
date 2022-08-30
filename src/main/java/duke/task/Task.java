package duke.task;

import java.time.LocalDate;

/**
 * Abstract class that is the parent class to all tasks.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Converts the task into a string representation that can be saved into a file.
     *
     * @return String representation of the task.
     */
    public abstract String save();

    /**
     * Converts a string representation of a task from a save file into a Task.
     * @param task String representation of a task.
     * @return The task.
     */
    public static Task load(String task) {
        String[] split = task.split(" \\| ", 4);
        String taskType = split[0];
        boolean isDone = split[1].equals("1");
        String description = split[2];
        LocalDate time = split.length == 4 ? LocalDate.parse(split[3]) : null;

        if (taskType.equals("T")) {
            return new ToDo(description, isDone);
        } else if (taskType.equals("E")) {
            return new Event(description, time, isDone);
        } else {
            return new Deadline(description, time, isDone);
        }
    }

    /**
     * Returns the time of the task, returning LocalDate.MIN if the task is a ToDo.
     *
     * @return Time of the task.
     */
    public abstract LocalDate getTime();
}
