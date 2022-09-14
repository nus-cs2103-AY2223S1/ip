package duke;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *  Represents an event task in the task list
 * @author Reuben Chay
 */
public class Event extends Task {

    private LocalDate date;
    private LocalDateTime dateTime;

    /**
     * Constructs event class ie. constructor
     * @param name name of task
     * @param dateTime date and time of task in string format
     */
    Event(String name, String dateTime) {
        super(name);
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.dateTime = LocalDateTime.parse(dateTime, format);
        } catch (DateTimeParseException e) {
            System.out.println("Incorrect date format");
            this.dateTime = null;
        }
    }

    Event(String name, String dateTime, boolean isExist) {
        super(name);
        this.dateTime = LocalDateTime.parse(dateTime,
                        DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }


    @Override
    boolean isToDo() {
        return false;
    }

    /**
     * Returns date and time of task
     * @return date and time of task using LocalDateTime library
     */
    @Override
    LocalDateTime getDateTime() {
        return this.dateTime;
    }

    /**
     * Updates the date associated with this task
     * @param newDate string containing the new date and time
     */
    @Override
    void editDate(String newDate) {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.dateTime = LocalDateTime.parse(newDate, format);
        } catch (DateTimeParseException e) {
            System.out.println("Incorrect date format");
        }
    }

    @Override
    public String toString() {
        String out = "[E]";
        out += super.toString();
        out += " (at " + this.dateTime.format(DateTimeFormatter.
                        ofPattern("MMM dd yyyy HHmm")) + ")";
        return out;
    }
}
