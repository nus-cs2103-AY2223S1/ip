package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//@@author chengda300
//Reused from https://nus-cs2103-ay2223s1.github.io/website/schedule/week2/project.html
// with minor modifications

/**
 * Represents a task with deadline. Such tasks have a task description and a deadline.
 */
public class Deadline extends Task {
    private String by;
    private LocalDate date;

    /**
     * Constructor for a task with deadline.
     *
     * @param description Task description.
     * @param by Deadline in String format.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for a task with deadline.
     *
     * @param description Task description.
     * @param by Deadline in LocalDate format.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.date = by;
    }

    /**
     * Returns a string representation of the task with deadline.
     *
     * @return String representation of task with deadline.
     */
    @Override
    public String toString() {
        String deadline = this.by == null
                ? this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : this.by;
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
//@@author
