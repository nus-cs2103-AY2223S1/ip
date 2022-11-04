package seedu.duke.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Todo extends Task{

    public Todo (String description) {
        super(description);
    }

    /**
     * Returns the short form of the type of this Task
     *
     * @return the short form of the type of this Task
     */
    @Override
    public String getType() {
        return "T";
    }

    /**
     * Returns the long form of the type of this Task
     *
     * @return the long form of the type of this Task
     */
    @Override
    public String getTypeLong() {
        return "todo";
    }

    /**
     * Returns the String object representing this Task
     *
     * @return the String object representing this Task
     */
    @Override
    public String toString() {
        return "[" + this.getType() + "]" + "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns the String object to be stored as data representing this Task
     *
     * @return the String object to be stored as data representing this Task
     */
    @Override
    public String getData() {
        return getTypeLong() + "|" + this.description + "|" + this.getStatus();
    }

}
