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
    private String deadline;
    private LocalDate date;

    /**
     * Constructor for a task with deadline.
     *
     * @param description Task description.
     * @param deadline Deadline in String format.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        assert this instanceof Task : "Deadline should be a task.";
    }

    /**
     * Constructor for a task with deadline.
     *
     * @param description Task description.
     * @param deadline Deadline in LocalDate format.
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.date = deadline;
    }

    /**
     * Returns a string representation of the task with deadline.
     *
     * @return String representation of task with deadline.
     */
    @Override
    public String toString() {
        String deadline = this.deadline == null
                ? this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : this.deadline;
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
//@@author
