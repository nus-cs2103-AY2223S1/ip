package pixel.task;

import java.util.Objects;

/**
 * Represents an Event which is type of Task
 * Has tag E
 */
public class Event extends Task {

    public static final String TAG = "E";

    /**
     * Constructor for a new event object
     *
     * @param description description of the task
     * @param due due day/ date and time of the task
     * @param commandWord "at" or "by"
     */
    public Event (String description, String due, String commandWord) {
        super(description, due, commandWord);
    }

    /**
     * toString method
     *
     * @return String representation of the Event object
     */
    @Override
    public String toString() {
        String end = Objects.equals(this.commandWord, "")
            ? this.commandWord
            : " (" + this.commandWord + ": " + this.due + ")";

        return "[" + TAG + "]" + super.toString() + end;
    }
}
