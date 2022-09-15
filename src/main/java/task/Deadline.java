package task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * Deadline task is a type of Task with a deadline.
 */
public class Deadline extends Task {
    protected String by;

    protected String string_Date;

    protected LocalDate date;

    protected String string_Time;


    /**
     * Creates a new Deadline.
     * @param description The name of activity.
     * @param by The time to do the deadline by.
     */
    public Deadline(String description, String by) {

        super(description);
        this.by = by;
        String[] dateAndTime = by.split(" ", 4);
        this.string_Date = dateAndTime[1] + "/" + dateAndTime[0] + "/" + dateAndTime[2];
        this.string_Time = dateAndTime[3];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
        this.date = LocalDate.parse(string_Date, formatter);

    }

    @Override
    public String toString() {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String output = date.format(formatter1);
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
