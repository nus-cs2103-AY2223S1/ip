package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline Task object.
 *
 * @author ShummyOwnzYou
 * @version 0.1
 */

public class Deadline extends Task {
    private LocalDate time;

    /**
     * Initializes Deadline Task object with the task description and Deadline.
     *
     * @param name Task name to be stored
     * @param time Deadline to be stored
     */

    public Deadline(String name, LocalDate time) {
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
        return "D##" + super.stringify() + "##" + this.time;
    }

    /**
     * Returns the String representation of the Task.
     *
     * @return the String representation of the Task
     */

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

