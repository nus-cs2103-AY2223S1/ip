package JennyTasks;

import Exceptions.TodoTaskException;

/**
 * A TodoJennyTask to complete.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class TodoJennyTask extends JennyTask {

    /**
     * Constructor of a TodoJennyTask.
     * @param description description of the task.
     */
    public TodoJennyTask(String description) {
        super(description);
        if (description.equals("")) {
            throw new TodoTaskException("☹ OOPS!!! The description of a todo cannot be empty.");
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
