package duke.task;

import duke.Task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task
 */
public class Deadline extends Task {
    /** Date of the deadline */
    private LocalDate date;

    public Deadline(String item, LocalDate date) {
        this.setItem(item);
        this.date = date;
    }

    /**
     * Gets the string representation of the task
     * @return string representation of the task
     */
    public String getTask() {
        String d = this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D] " + this.getStatusIcon() + " " + this.getItem() + " (by: " + d + ")";
    }

    /**
     * Gets the representation of the task that is stored in the data file
     * @return string representation of the task that is stored in the data file
     */
    public String getFileLine(){
        String d = this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D]" + "##" + this.getStatusIcon() + "##" + this.getItem() + "##" + d;
    }
}