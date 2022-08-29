package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class that creates an object of type Deadline
 * which are tasks that have a deadline with keyword "by".
 *
 * @author Safwan A0235287X
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDate date;

    /**
     * Constructor to create Deadline object.
     * @param description Content of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Method that overrides Java.toString() method to convert the Deadline
     * task as a string.
     * @return A string of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }

    /**
     * Method to obtain the date as an attribute of its own rather than a string
     * in the format of "MMM d yyyy".
     */
    public void getDate() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate deadline = LocalDate.parse(this.by, formatter);
            this.date = deadline;
            this.by = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

