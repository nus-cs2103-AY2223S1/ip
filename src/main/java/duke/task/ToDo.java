package duke.task;

import java.time.LocalDate;

import duke.exception.InvalidInputException;

/**
 * ToDo is a task that has to be done.
 *
 * @author Eugene Tan
 */
public class ToDo extends Task {

    /**
     * Constructor of ToDo Task.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor of ToDo Task.
     *
     * @param description The description of teh toDo task.
     * @param isDone Whether the task has been done.
     */
    public ToDo(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void updateDate(LocalDate date) throws InvalidInputException {
        throw new InvalidInputException("There is no date to update for ToDo");
    }

    /**
     * Converts toDo task to String format to be saved.
     *
     * @return String format to be saved.
     */
    @Override
    public String saveStringFormat() {
        return String.format("T | %d | %s", this.isDone ? 1 : 0, this.description);
    }

    /**
     * Returns String representation of the toDo task.
     *
     * @return String representation of toDo task.
     */
    @Override
    public String toString() {
        return "[T] " + "[" + this.getStatusIcon() + "] " + this.description;
    }
}
