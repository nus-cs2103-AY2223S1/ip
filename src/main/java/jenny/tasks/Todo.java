package jenny.tasks;

import jenny.exceptions.TaskException;

/**
 * A Todo to complete.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class Todo extends AbstractTask {

    /**
     * Constructor of a Todo.
     *
     * @param description description of the task.
     */
    public Todo(String description) {
        super(description);
        if (description.equals("")) {
            throw new TaskException(String.format("%s: The description of this task cannot be empty.",
                    this.getClass().getSimpleName()));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
