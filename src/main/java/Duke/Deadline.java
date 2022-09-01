package duke;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline-type task
 * @author Reuben Chay
 */
public class Deadline extends Task {

    private LocalDate date;
    private LocalDateTime dateTime;

    /**
     * Constructs a deadline class
     * @param name name of task
     * @param dateTime date and time of task
     */
    Deadline(String name, String dateTime) {
        super(name);
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.dateTime = LocalDateTime.parse(dateTime, format);
        } catch (DateTimeParseException e) {
            System.out.println("Incorrect date format");
            this.dateTime = null;
        }
    }

    Deadline(String name, String dateTime, boolean exist) {
        super(name);
        this.dateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }

    /**
     * Returns if type of class is to-do
     * @return true if type is to-do, false otherwise
     */
    @Override
    boolean isToDo() {
        return false;
    }

    @Override
    LocalDateTime getDateTime() {
        return this.dateTime;
    }

    @Override
    public String toString() {
        String out = "[D]";
        out += super.toString();
        out += " (by " + this.dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
        return out;
    }

}

