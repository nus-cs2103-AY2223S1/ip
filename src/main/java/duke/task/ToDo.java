package duke.task;

import duke.common.DukeException;

/**
 * Represents a to-do type task.
 *
 * @author Rama Aryasuta Pangestu
 */
public class ToDo extends Task {
    /**
     * Constructs a to-do task.
     *
     * @param description the description of the task
     * @param isDone      denotes whether the task is marked done or not
     * @throws DukeException if the description of the task is empty
     */
    public ToDo(String description, boolean isDone) throws DukeException {
        super("todo", description, isDone);
        assert !description.isBlank();
    }
}
