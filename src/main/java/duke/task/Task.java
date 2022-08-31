package duke.task;

import duke.common.DukeException;
import java.time.LocalDate;

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

    public String encode() {
        return this.description + Task.ENCODING_SEPARATOR + this.isDone;
    }

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
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}