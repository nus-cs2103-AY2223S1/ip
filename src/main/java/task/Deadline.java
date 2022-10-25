package task;

import java.lang.annotation.Documented;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline Task object.
 *
 * @author ShummyOwnzYou
 * @version 0.1
 */

public class Deadline extends Task {

    private final LocalDate time;
    private final boolean isOver;

    /**
     * Initializes Deadline Task object with the task description and Deadline.
     *
     * @param name Task name to be stored
     * @param time Deadline to be stored
     */

    public Deadline(String name, LocalDate time) {
        super(name);
        this.time = time;
        this.isOver = LocalDate.now().isAfter(time);
    }

    /**
     * Returns true if the current date is after the deadline, false otherwise.
     *
     * @return true if the current date is after the deadline, false otherwise.
     */

    @Override
    public boolean isOver() {
        return isOver;
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
        if (isOver) {
            return "[D]" + super.toString() + " (by: "
                    + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " OVERDUE!)";
        } else {
            return "[D]" + super.toString() + " (by: "
                    + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }
}

