package pixel.task;

import java.util.Objects;

/**
 * Represents an ToDo which is type of Task
 * Has tag T
 */
public class ToDo extends Task {

    public static final String TAG = "T";

    /**
     * Constructor for a new ToDo object
     *
     * @param description description of the task
     * @param due due day/ date and time of the task
     * @param commandWord "at" or "by"
     */
    public ToDo (String description, String due, String commandWord) {
        super(description, due, commandWord);
    }

    /**
     * toString method
     *
     * @return String representation of the ToDo object
     */
    @Override
    public String toString() {
        String end = Objects.equals(this.commandWord, "")
            ? this.commandWord
            : " (" + this.commandWord + ": " + this.due + ")";

        return "[" + this.TAG + "]" + super.toString() + end;
    }
}
