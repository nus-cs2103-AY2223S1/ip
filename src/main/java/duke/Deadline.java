package duke;

import java.time.LocalDate;

/**
 * Deadline is a type of task with a deadline timing.
 */
public class Deadline extends Task {

    protected LocalDate timeobject;

    /**
     * Constructor of deadline.
     *
     * @param description description of deadline.
     * @param time deadline time.
     */
    public Deadline(String description, String time) {
        super(description);
        this.timeobject = Parser.stringToDate(time);

    }

    @Override
    public String storeToString() {
        return "D|" + this.binarytoString() + "|" + this.description.substring(0, description.length()) + "|"
                + Parser.dateToString(this.timeobject);
    }

    /**
     * Returns string representation of the object.
     *
     * @return string string representation of the object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.displayDate(timeobject) + ")";
    }

}