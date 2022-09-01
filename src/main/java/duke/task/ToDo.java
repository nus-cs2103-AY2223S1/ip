package duke.task;

/**
 * Represents an ToDo type of Task object.
 * A ToDo type of Task only has a description, but no deadline to complete it.
 */
public class ToDo extends Task {

    private static final String TODO_LETTER = "T";


    /**
     * Creates a new ToDo object.
     * 
     * @param description Description of the ToDo object.
     */
    public ToDo(String description) {
        this(description, false);
    }

    private ToDo(String description, boolean isDone) {
        super(description, isDone);
    }
    

    @Override
    public ToDo markTask() {
        return new ToDo(this.description, true);
    }


    @Override
    public ToDo unmarkTask() {
        return new ToDo(this.description, false);
    }


    /**
     * Returns the string representation of the ToDo object.
     * 
     * @return String representation of the ToDo object.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s", TODO_LETTER, super.toString());
    }

}
