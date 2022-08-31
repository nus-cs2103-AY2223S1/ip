package duke.task;

import duke.common.DukeException;
import java.time.LocalDate;

/**
 * Abstract class to capture a task's description and completion status.
 *
 * @author Tan Jun Wei
 */
public abstract class Task {
    protected static final String ENCODING_SEPARATOR = "||";
    protected static final String ENCODING_SEPARATOR_REGEX = "\\|\\|";
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns an encoded string representing the task.
     *
     * @return An encoded string representing the task.
     */
    public String encode() {
        return this.description + Task.ENCODING_SEPARATOR + this.isDone;
    }

    /**
     * Returns a task object from an encoded string.
     *
     * @param encodedTask The encoded string to decode.
     * @return The task object.
     */
    public static Task decode(String encodedTask) throws DukeException {
        String[] taskInfo = encodedTask.split(Task.ENCODING_SEPARATOR_REGEX);
        Task task;
        switch(taskInfo[0]) {
            case "E":
                task = new Event(taskInfo[2], LocalDate.parse(taskInfo[1]), Boolean.parseBoolean(taskInfo[3]));
                break;
            case "D":
                task = new Deadline(taskInfo[2], LocalDate.parse(taskInfo[1]), Boolean.parseBoolean(taskInfo[3]));
                break;
            case "T":
                task = new ToDo(taskInfo[1], Boolean.parseBoolean(taskInfo[2]));
                break;
            default:
                throw new DukeException("Invalid task encoding: " + encodedTask);
        }
        return task;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}