package bloop;

/**
 * Task to do.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo object.
     *
     * @param task Task to be performed.
     */
    public ToDo(String task) {
        super(task);
    }

    public String getBy() {
        return "";
    }

    public char getType() {
        return 'T';
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
