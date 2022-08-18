package TaskItems;

import Exceptions.TodoTaskException;

/**
 * A TodoTask to complete.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class TodoTask extends TaskItem {

    /**
     * Constructor of a TodoTask.
     * @param description description of the task.
     */
    public TodoTask(String description) {
        super(description);
        if (description.equals("")) {
            throw new TodoTaskException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Returns the description of the task as a string.
     * @return the description of the task as a string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
