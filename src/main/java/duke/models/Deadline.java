package duke.models;

import duke.utils.Interval;

public class Deadline extends Task {

    protected String by;
    protected FormattedDate formattedDate;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.formattedDate = new FormattedDate(by);
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
        this.formattedDate = new FormattedDate(by);
    }

    public Deadline(String description, boolean isDone, String at, Interval interval) {
        super(description, isDone, interval);
        this.formattedDate = new FormattedDate(at);
    }

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