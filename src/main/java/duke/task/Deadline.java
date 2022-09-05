/**
 * Project Duke CS2103
 * Done by Hong Jin.
 */
package duke.task;

import java.time.LocalDate;

/**
 * class Deadline to handle Deadline Task.
 */
public class Deadline extends Task {
    private LocalDate date;

    /**
     * public constructor for Deadline.
     * @param task
     */
    public Deadline(String task, LocalDate date) {
        super(task);
        this.date = date;
    }

    /**
     * class method to return String representation of Deadline Task.
     * @return String.
     */
    @Override
    public String toString() {
        return String.format("%s %s (by: %s)", "[D]", super.toString(), this.date.toString());
    }
}
