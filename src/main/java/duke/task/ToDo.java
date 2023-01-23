package duke.task;

import duke.DukeException;

import java.time.LocalDate;

/**
 * The ToDo class represents a task containing a string description only.
 */
public class ToDo extends Task {

    /**
     * Initializes an instance of ToDo that has the specified description and is not done.
     *
     * @param taskDescription Specified description of the todo.
     */
    public ToDo(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Initializes an instance of ToDo that has the specified description and state of completion.
     *
     * @param taskDescription Specified description of the todo.
     * @param isDone Indicates the state of completion the todo.
     */
    public ToDo(String taskDescription, boolean isDone) {
        super(taskDescription);
        this.isDoneSetter(isDone);
    }

    @Override
    public LocalDate getDate() throws DukeException {
        throw new DukeException("ToDo has no date details.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getTypeIcon() {
        return "[T]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toStorageString() {
        return "T" + "|" + super.toStorageString();
    }
}
