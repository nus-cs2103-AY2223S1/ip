/**
 * Todo is a Task without any date/time attached to it.
 */
public class Todo extends Task {
    /**
     * Private constructor for a Todo, with a description.
     * Todo is set as "not done" when created.
     *
     * @param description Description of a todo.
     */
    private Todo(String description) {
        super(description);
    }

    /**
     * Factory Method for a Todo, with a user input.
     * Todo is set as "not done" when created.
     *
     * @param input User input that starts with "todo".
     *
     * @return Todo object with the given user input.
     */
    public static Todo create(String input) throws DukeException {
        if (input.length() < 6) {
            // If user typed "todo" or "todo " or any other input less than 6 characters, the description will be empty.
            throw new DukeException("Description of Todo cannot be empty!");
        }
        // Obtain the description from the user input.
        String description = input.substring(5);
        return new Todo(description);
    }

    /**
     * Gets the string representation of a todo.
     *
     * @return String representation of a todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
