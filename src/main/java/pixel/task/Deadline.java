package pixel.task;

import java.util.Objects;

/**
 * Represents a Deadline which is type of Task
 * Has tag D
 */
public class Deadline extends Task {

    public final String tag = "D";

    /**
     * Constructor for a new deadline object
     *
     * @param description description of the task
     * @param due due day/ date and time of the task
     * @param commandWord "at" or "by"
     */
    public Deadline (String description, String due, String commandWord) {
        super(description, due, commandWord);
    }

    /**
     * toString method
     *
     * @return String representation of the Deadline object
     */
    @Override
    public String toString() {
        String end = Objects.equals(this.commandWord, "")
            ? this.commandWord
            : " (" + this.commandWord + ": " + this.due + ")";
        return "[" + this.tag + "]" + super.toString() + end;
    }
}