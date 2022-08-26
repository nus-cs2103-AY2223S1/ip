package duke.task;

import java.time.LocalDate;
import java.time.Month;

/**
 * Represents a deadline that has a name and a date that it should be done by
 */
public class Deadlines extends Task {
    private LocalDate byDate;

    /**
     * Constructor for the deadline object
     *
     * @param name
     * @param byDate
     */
    public Deadlines(String name, LocalDate byDate) {
        super(name);
        this.byDate = byDate;
    }

    /**
     * writeData method that formats the information in deadline
     *
     * @return String
     */
    @Override
    public String writeData() {
        int mark = isDone ? 1 : 0;
        return "D#" + mark + "#" + this.name + "#" + this.byDate;
    }

    /**
     * toString method that formats the information in deadline
     *
     * @return String
     */
    @Override
    public String toString() {
        int year = byDate.getYear();
        int day = byDate.getDayOfMonth();
        Month month = byDate.getMonth();
        return "[D]" + super.toString() + " (by: " + day + " " + month + " " + year + ")";
    }
}
