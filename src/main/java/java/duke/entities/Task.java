package duke.entities;

import duke.enums.Messages;
import duke.exceptions.DukeException;

/**
 * Task with a description
 */
public class Task {
    private Boolean isComplete = false;
    private String description;

    /**
     * Sets the description of the task
     * @param desc Description of the task
     */
    public Task(String desc) throws DukeException {
        if (desc == null) {
            throw new DukeException(Messages.ERROR_MISSING_PARAMETERS.toString());
        }
        this.description = desc;
    }

    /**
     * Flips the value isComplete
     */
    public void toggleComplete() {
        isComplete = !isComplete;
    }

    /**
     * Checks if the boolean is complete
     * @return true if completed else false
     */
    public boolean isDone() {
        return isComplete;
    }

    /**
     * Retrieves the description of the task
     * @return Description of task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the task contains the keyword
     * @param keyword keyword to search
     * @return true if the keyword is in else false
     */
    public boolean contains(String keyword) {
        return toString().contains(keyword);
    }
    @Override
    public String toString() {
        String marker = isComplete ? "[X] " : "[ ] ";
        return marker + description;
    }
}
