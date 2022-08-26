package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event Task object.
 *
 * @author ShummyOwnzYou
 * @version 0.1
 */

public class Event extends Task {
    private LocalDate time;

    /**
     * Initializes Event Task object with the task description and event date.
     *
     * @param name Task name to be stored
     * @param time Event date to be stored
     */

    public Event(String name, LocalDate time) {
        super(name);
        this.time = time;
    }

    /**
     * Converts the task into a string to be stored in the Storage class.
     *
     * @return the String containing the Task information to be stored
     */

    @Override
    public String stringify() {
        return "E##" + super.stringify() + "##" + this.time;
    }

    /**
     * Returns the String representation of the Task.
     *
     * @return the String representation of the Task
     */

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
