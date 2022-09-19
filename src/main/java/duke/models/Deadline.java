package duke.models;

import duke.utils.Interval;

/**
 * A class representing a deadline to be completed by a specified date.
 */
public class Deadline extends Task {
    protected String by;
    protected FormattedDate formattedDate;

    /**
     * Deadline Constructor.
     *
     * @param description Deadline description.
     * @param by Date to be completed by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.formattedDate = new FormattedDate(by);
    }

    /**
     * Deadline Constructor.
     *
     * @param description Deadline description.
     * @param isDone Completion status.
     * @param by Date to be completed by.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
        this.formattedDate = new FormattedDate(by);
    }

    /**
     * Deadline Constructor.
     *
     * @param description Deadline description.
     * @param isDone Completion status.
     * @param by Date to be completed by.
     * @param interval Recurring interval.
     */
    public Deadline(String description, boolean isDone, String by, Interval interval) {
        super(description, isDone, interval);
        this.formattedDate = new FormattedDate(by);
    }

    /**
     * Deadline Constructor.
     *
     * @param description Deadline description.
     * @param isDone Completion status.
     * @param formattedDate Date to be completed by.
     * @param interval Recurring interval.
     */
    public Deadline(String description, boolean isDone, FormattedDate formattedDate, Interval interval) {
        super(description, isDone, interval);
        this.formattedDate = formattedDate;
    }

    /**
     * Gets the formatted date of the Deadline.
     *
     * @return Deadline by date.
     */
    public FormattedDate getFormattedDate() {
        return this.formattedDate;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.formattedDate);
    }
}
