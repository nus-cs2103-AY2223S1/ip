package duke.task;

import java.time.LocalDate;

/**
 * Child class of Task with a deadline
 */
public class Deadline extends Task {
    private static final String SYMBOL = "[D]";
    protected String dateStr;
    protected LocalDate dateTime;

    /**
     * Constructor for the Deadline class
     *
     * @param description description of the deadline
     * @param dateStr date of the deadline in string
     * @param dateTime date of the deadline in LocalDate class
     */
    public Deadline(String description, String dateStr, LocalDate dateTime) {
        super(description, SYMBOL);
        this.dateStr = dateStr;
        this.dateTime = dateTime;
    }

    /**
     * Method to get the date of the deadline
     *
     * @return the date of the deadline
     */
    public String getDate() {
        return this.dateStr;
    }

    /**
     * Method to get the info of the event
     * in the format of [D]--[ ]--{deadline name}--{date}
     *
     * @return the info of the deadline
     */
    @Override
    public String getInfo() {
        return (super.getInfo() + "--"
                + getDate());
    }

    /**
     * Method to return a string representation of the deadline
     * @return string representation of the deadline
     */
    @Override
    public String toString() {
        return SYMBOL + super.toString()
                + " (by: " + dateStr + ")";
    }
}
