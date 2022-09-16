package task;

import duke.DukeException;
import ui.UI;

import java.lang.reflect.InvocationTargetException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Deadline task is a type of Task with a deadline.
 */
public class Deadline extends Task {

    protected static UI ui = new UI();
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
        String[] dateAndTime = by.split(" ");
        DateTimeFormatter formatter;
        try {
            if (dateAndTime.length == 4) {
                this.string_Date = dateAndTime[1] + "/" + dateAndTime[0] + "/" + dateAndTime[2];
                this.string_Time = dateAndTime[3];
                formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
            } else {
                this.string_Date = dateAndTime[0];
                this.string_Time = dateAndTime[1];
                formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ui.noDateOrTime());
        }
        try {
            this.date = LocalDate.parse(string_Date, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException(ui.wrongDateTimeFormat());
        }

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
