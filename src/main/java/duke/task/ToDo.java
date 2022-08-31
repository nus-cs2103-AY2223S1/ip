package duke.task;

import duke.exception.DukeException;

/**
 * Represents a todo task.
 */
public class ToDo extends Task {

    /**
     * Adds the description of a task.
     * The userInput argument must contain a description of the todo task.
     * <p>
     * If the description is empty, the function will throw a DukeException.
     *
     * @param userInput a String containing the description of a task
     * @throws DukeException
     */
    @Override
    public void addName(String userInput) throws DukeException {
        if (userInput.length() <= 5) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        super.addName(userInput.substring(5));
    }

    /**
     * Returns a String in a format that will be stored in the specified directory.
     * The String will contain the type of task and the current status (marked or unmarked) of the task.
     *
     * @return the details of the task for Storage
     */
    @Override
    public String getTask() {
        return String.format("T | " + super.getTask());
    }

    /**
     * Returns a String that will display the task.
     * The String will contain the type of task and the current status
     * (marked or unmarked) of the task.
     *
     * @return the details of the task for display
     */
    @Override
    public String getStatus() {
        return String.format("[T]%s", super.getStatus());
    }
}
