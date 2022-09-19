package duke.models;

import duke.utils.Interval;

/**
 * A class representing a deadline to be completed byDate a specified date.
 */
public class Deadline extends Task {
    protected FormattedDate byDate;

    /**
     * Deadline Constructor.
     *
     * @param description Deadline description.
     * @param byDate Date to be completed byDate.
     */
    public Deadline(String description, FormattedDate byDate) {
        super(description);
        this.byDate = byDate;
    }

    /**
     * Deadline Constructor.
     *
     * @param description Deadline description.
     * @param isDone Completion status.
     * @param byDate Date to be completed by.
     */
    public Deadline(String description, boolean isDone, FormattedDate byDate) {
        super(description, isDone);
        this.byDate = byDate;
    }

    /**
     * Deadline Constructor.
     *
     * @param description Deadline description.
     * @param isDone Completion status.
     * @param byDate Date to be completed by.
     * @param interval Recurring interval.
     */
    public Deadline(String description, boolean isDone, FormattedDate byDate, Interval interval) {
        super(description, isDone, interval);
        this.byDate = byDate;
    }

    /**
     * Gets the formatted date of the Deadline.
     *
     * @return Deadline byDate date.
     */
    public FormattedDate getDeadlineDate() {
        return this.byDate;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (byDate: %s)", super.toString(), this.byDate);
    }
}
