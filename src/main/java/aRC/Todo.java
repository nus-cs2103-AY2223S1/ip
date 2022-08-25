package aRC;

/**
 * Encapsulates a Todo task
 */
public class Todo extends Task {
    /**
     * Constructor for aRC.Todo
     * @param title The title of aRC.Todo
     * @param isDone The isDone status of the aRC.Todo
     */
    public Todo(String title, boolean isDone) {
        super(title, isDone);
    }

    /**
     * Returns how a Todo should be represented
     * @return String representation of Todo
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Returns how a Todo should be stored in a txt file
     * @return String representation of Todo
     */
    @Override
    public String fileFormat() {
        return String.format("T|%d|%s", this.isDone ? 1 : 0, this.title);
    }
}
