package duke.task;

/**
 * Represents a to-do task
 */
public class ToDo extends Task{

    /**
     * Creates a new to-do task
     *
     * @param description the description of this to-do
     */
    public ToDo(String description){
        super(description);
    }

    /**
     * Returns a string representation of this to-do
     *
     * @return string representation
     */
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

    /**
     * Returns a simplified string representation of this to-do
     *
     * @return string representation
     */
    @Override
    public String toSimpleString() {
        return "T | " + super.toSimpleString();
    }
}
