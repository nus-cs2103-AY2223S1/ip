package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * The Deadline task which represents a deadline.
 *
 * @author Leong Jia Hao Daniel
 */
public class Deadline extends Task {

    protected LocalDate date;
    protected String time;
    protected String dateTime;

    /**
     * The constructor for a Deadline task.
     *
     * @param description The description of the task.
     * @param dateTime The data and time of the task in the d/MM/yyyy format.
     */
    public Deadline(String description, String dateTime) {
        super(description);
        String[] details = dateTime.split(" ");
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/MM/yyyy");
        this.date = LocalDate.parse(details[0], inputFormat);
        this.time = details[1];
        this.dateTime = dateTime;

    }

    /**
     * Reads from the input file and returns a deadline based on the data
     * in the file.
     *
     * @param string The line in the file.
     * @return The Deadline task.
     */
    public static Deadline parseFile(String string) {
        String[] details = string.split(" \\| ");
        Deadline deadline = new Deadline(details[2], details[3]);
        if (details[1].equals("1")) {
            deadline.markAsDone();
        }
        return deadline;
    }

    /**
     * Overrides the toDataFormat() in task to return a String which
     * is stored in the file.
     *
     * @return The task but formatted in the way it is meant to
     *         be stored in the file.
     */
    @Override
    public String toDataFormat() {
        String completed = "0";
        if (this.getStatusIcon().equals("X")) {
            completed = "1";
        }
        return "D | " + completed + " | " + this.getDescription() + " | " + this.dateTime;
    }

    /**
     * Override the toString() method to display the task to the user.
     *
     * @return A String representing the deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString()
                + " (by: " + time + " " + date.format(displayFormat) + ")";
    }
}
