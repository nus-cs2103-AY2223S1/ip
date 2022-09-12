package duke;

import java.time.LocalDate;

/**
 * Represents the Deadline task which is a type of task
 * It has the tag [D] and the time would be represented by "by 2022-10-23"
 */
public class Deadline extends Task {
    private final LocalDate date;

    /**
     * Constructor for Deadline class
     *
     * @param name     task name
     * @param deadline task deadline
     */
    public Deadline(String name, String deadline) {
        super(name);
        this.date = Parser.convertStringToDate(deadline);

    }

    /**
     * Returns the string representation for deadline.
     *
     * @return String representation of deadline
     */
    @Override
    public String toString() {
        String type = "[D]";
        return type + "[" + this.getStatusIcon() + "] "
                + this.getTaskName() + "(" + Parser.convertDateToString(date) + ") "
                + this.getTagString();
    }
}
