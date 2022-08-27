package roofus.task;

import java.time.LocalDate;

/**
 * Deadline is a type of Task that contains a description,
 * boolean value that indicates if it is completed and
 * a LocalDate attribute that represents the deadline of the task.
 */
public class Deadline extends Task {
    private LocalDate date;

    /**
     * Constructs an instance of Deadline.
     *
     * @param description A description of the Deadline instance.
     * @param date The date at which the Deadline instance must be done.
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = LocalDate.parse(date);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String writeString() {
        return String.format("D | %d | %s | %s",
                super.isDone ? 1 : 0, super.description, date);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[D]%s by: %s",
                super.toString(), this.date.toString());
    }
}
