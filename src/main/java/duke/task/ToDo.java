package duke.task;

import java.time.LocalDate;

/**
 * A task that is a ToDo.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo.
     *
     * @param description Description of the ToDo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructor for ToDo.
     *
     * @param description Description of the ToDo.
     * @param done Status of the task.
     */
    public ToDo(String description, boolean done) {
        super(description);
        this.isDone = done;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the task into a string representation that can be saved into a file.
     *
     * @return String representation of the task.
     */
    @Override
    public String save() {
        return "T | " + (isDone ? "1" : "0") + " | " + this.description;
    }

    /**
     * Returns the time of the task, returning LocalDate.MIN if the task is a ToDo.
     *
     * @return Time of the task.
     */
    @Override
    public LocalDate getTime() {
        return LocalDate.MIN;
    }
}