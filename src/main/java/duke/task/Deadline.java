package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * A Deadline task
 *
 * @author Nephelite
 * @version 0.1
 */
public class Deadline extends Task {
    private final LocalDate deadline;

    /**
     * Constructor for a Deadline task
     *
     * @param task the task
     * @param deadline the deadline
     * @since 0.1
     */
    public Deadline(String task, String deadline) {
        super(task, "deadline");
        String[] returnedArray = deadline.split(" ");
        if (returnedArray.length == 1) {
            this.deadline = LocalDate.parse(deadline);
        } else {
            this.deadline = LocalDate.parse(deadline,
                    DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        }
    }

    /**
     * Returns a String representation of a Deadline
     *
     * @return String representation of a Deadline
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: "
                + deadline.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)) + ")";
    }
}
