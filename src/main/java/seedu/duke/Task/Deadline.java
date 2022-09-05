package seedu.duke.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    String type = "deadline";

    public Deadline (String description, LocalDate date) {
        super(description, date);
    }


    /**
     * Returns the short form of the type of this Task
     *
     * @return the short form of the type of this Task
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Returns the long form of the type of this Task
     *
     * @return the long form of the type of this Task
     */
    @Override
    public String getTypeLong() {
        return "deadline";
    }

    /**
     * Returns the String object representing this Task
     *
     * @return the String object representing this Task
     */
    @Override
    public String toString() {
        return "[" + this.getType() + "]" + "[" + this.getStatusIcon() + "] " + this.description
                        + "(by " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns the String object to be stored as data representing this Task
     *
     * @return the String object to be stored as data representing this Task
     */
    @Override
    public String getData() {
        return getTypeLong() + "|" + this.description + "|" + this.getStatus() + "|" + this.date;
    }

}
