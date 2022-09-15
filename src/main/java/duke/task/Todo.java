package duke.task;

/**
 * A todo task
 *
 * @author Pontakorn Prasertsuk
 */
public class Todo extends Task {

    public static final String SYMBOL = "T";

    /**
     * Constructs a new Todo instance
     *
     * @param title the name of the task
     * @param isDone whether the task is completed or not
     */
    public Todo(String title, boolean isDone) {
        super(title, isDone);
    }

    /**
     * Encodes the task for saving into the file
     *
     * @return the string to be saved into the file
     */
    @Override
    public String encode() {
        return SYMBOL + " | " + (this.isDone ? "1" : "0") + " | " + this.title;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
