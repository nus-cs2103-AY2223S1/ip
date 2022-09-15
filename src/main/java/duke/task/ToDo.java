package duke.task;

import duke.exception.DukeException;

/**
 * This class inherits from the abstract Task class
 * and encapsulates the logic of a ToDo task.
 */
public class ToDo extends Task {
    /**
     * Constructor for the Todo Task.
     *
     * @param description Description of the task.
     */
    public ToDo(String description, Priority priority) {
        super(description, priority);
    }

    /**
     * Factory method for creating a ToDo with user input.
     *
     * @param in User's input.
     * @return Todo with the relevant input fields.
     * @throws DukeException If incorrect formatting of todo task.
     */
    public static ToDo createToDo(String in) throws DukeException {
        String[] prioritySplit = splitPriority(in);

        String description = prioritySplit[0];
        String priorityString = prioritySplit[1];

        Priority priority = parsePriority(priorityString);

        return new ToDo(description, priority);
    }

    /**
     * Overrides toString method for the Todo Task.
     *
     * @return String representation of the Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + " " + priority;
    }

    /**
     * Overrides save format method from Task class.
     *
     * @return Formatted String for the Event task.
     */
    @Override
    public String saveFormat() {
        return String.format("T | %s", super.saveFormat());
    }
}
