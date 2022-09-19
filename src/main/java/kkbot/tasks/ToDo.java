package kkbot.tasks;

/**
 * ToDos are task objects that only have a description.
 *
 * @author AkkFiros
 */
public class ToDo extends Task {
    private static final String TODO_SYMBOL = "T";
    /**
     * Constructor for a ToDo task
     * @param description description of a task that the user inputs
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * toString method for a ToDo task
     * @return string representation of a ToDo task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s",
                ToDo.TODO_SYMBOL, super.toString());
    }

    /**
     * Method to retrieve a ToDo task's type
     * @return Symbol representation of a ToDo task
     */
    @Override
    public String getType() {
        return ToDo.TODO_SYMBOL;
    }

    /**
     * Returns the date of a ToDo task
     * @return A blank string (ToDo tasks do not have dates)
     */
    @Override
    public String getDate() {
        return " ";
    }
}
