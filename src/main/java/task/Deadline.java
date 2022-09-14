package task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * Deadline task is a type of Task with a deadline.
 */
public class Deadline extends Task {
    protected String by;

    protected String output;

    protected String string_Date;

    protected LocalDate date;

    protected String string_Time;

    protected LocalDate dateUse;


    /**
     * Creates a new Deadline.
     * @param description The name of activity.
     * @param by The time to do the deadline by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        String[] dateAndTime = by.split(" ");;
        this.string_Date = dateAndTime[0];
        this.string_Time = dateAndTime[1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        this.date = LocalDate.parse(string_Date, formatter);
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MMM dd yyyy");
        output = date.format(formatter1);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                output + " " + string_Time + ")";
    }


    /**
     * Checks if the due date is within 7 days of current user time.
     * @return True if due date is within 7 days of current user time.
     */
    public boolean isDueSoon() {
        LocalDate currentDate = LocalDate.now();
        LocalDate deadLine = currentDate.plusDays(6);
        return (!date.isAfter(deadLine) && !date.isBefore(currentDate));
    }
}
