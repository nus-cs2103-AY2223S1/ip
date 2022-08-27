package duke.task;

public class Todo extends Task {
    /**
     * Child class of Task without any date/time attached to it
     */
    private static final String SYMBOL = "[T]";

    /**
     * Constructor for the Todo class
     *
     * @param description description of the todo
     */
    public Todo(String description) {
        super(description, SYMBOL);
    }

    /**
     * Method to get the string info of the event
     * in the format of [T][ ] {tasl name}
     *
     * @return the date of the task
     */

    /**
     * Method to return a string representation of the todo task
     * @return string representation of the todo task
     */
    @Override
    public String toString() {
        return SYMBOL + super.toString();
    }
}
