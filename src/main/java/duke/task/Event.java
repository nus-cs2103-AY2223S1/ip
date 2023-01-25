/**
 * Project Duke CS2103
 * Done by Hong Jin.
 */
package duke.task;

import java.time.LocalDate;

/**
 * class Event to handle Event Task.
 */
public class Event extends Task {
    private LocalDate date;

    /**
     * public constructor for Event.
     * @param task
     */
    public Event(String task, LocalDate date) {
        super(task);
        this.date = date;
    }

    /**
     * class method to return String representation of Event Task.
     * @return String.
     */
    @Override
    public String toString() {
        return String.format("%s %s (at: %s)", "[E]", super.toString(), this.date.toString());
    }
}
