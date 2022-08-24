package duke.task;

public class Todo extends Task {
    /**
     * Child class of Task without any date/time attached to it
     */
    private static final String SYMBOL = "[T]";

    public Todo(String description) {
        super(description, SYMBOL);
    }

    /**
     * Method to get the string info of the event
     * in the format of [T][ ] {tasl name}
     *
     * @return the date of the task
     */
    @Override
    public String toString() {
        return SYMBOL + super.toString();
    }
}
