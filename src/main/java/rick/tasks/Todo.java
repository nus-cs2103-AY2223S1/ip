package rick.tasks;

import rick.RickException;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo object.
     * 
     * @param description The description of the task.
     */
    public Todo(String description) throws RickException {
        super(description);
    }

    /**
     * Constructs a Todo object.
     * 
     * @param description The description of the task.
     * 
     * @param isDone      The status of the task.
     */
    public Todo(String description, boolean isDone) throws RickException {
        super(description, isDone);
        assert (isValidInput(description));
    }

    /**
     * Validates the string input
     * 
     * @return boolean of whether the input is valid
     */
    public static boolean isValidInput(String input) throws RickException {
        if (input.isBlank()) {
            throw new RickException("Morty, I can't process your instruction! Your input can't be blank!");
        }
        return true;
    }

    /**
     * Returns the string representation of the Todo object.
     * 
     * @return The string representation of the Todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the Todo object to be saved.
     * 
     * @return The string representation of the Todo object to be saved.
     */
    @Override
    public String save() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
