package duke.task;

/**
 * Represents a To-Do task.
 */
public class ToDo extends Task {
    public static final String TAG = "T";

    /**
     * Constructor for a To-Do with a known completion status.
     *
     * @param name
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Constructor for a To-Do with a known completion status.
     *
     * @param name
     * @param isDone
     */
    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * toString method for a To-Do.
     *
     * @return To-do list friendly representation of a To-Do.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s", TAG, super.toString());
    }

    /**
     * Returns an To-Do in a save-friendly format.
     *
     * @return save-friendly representation of an To-Do.
     */
    @Override
    public String savedString() {
        return String.format("%s,%s", TAG, super.savedString());
    }

}
