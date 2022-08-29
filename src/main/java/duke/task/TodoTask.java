package duke.task;

import duke.DukeException;

/**
 * Class representing a TodoTask.
 */
public class TodoTask extends Task {
    /**
     * Class constructor for TodoTask.
     * 
     * @param description Command string being used to create TodoTask.
     * @throws DukeException If command is invalid.
     */
    public TodoTask(String description) throws DukeException {
        super();
        this.commandString = description;
        description = description.substring(5);
        if (description.length() <= 0) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        this.description = description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
