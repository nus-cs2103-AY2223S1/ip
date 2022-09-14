package duke;

import java.time.LocalDate;

/**
 * An abstraction of a task with a deadline.
 */
public class Deadline extends Task {
    private LocalDate deadline;
    private int period;

    /**
     * Constructor for the Deadline class.
     *
     * @param description The task description.
     * @param deadline The task deadline.
     */
    public Deadline(String description, LocalDate deadline, int period) {
        super(description);
        this.deadline = deadline;
        this.period = period;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public LocalDate getDate() {
        return deadline;
    }

    @Override
    public int getPeriod() {
        return period;
    }

    @Override
    public void updateDate() {
        if (period == 0) {
            return;
        }
        if (!this.getMarked()) {
            return;
        }
        this.setMarked(false);
        this.deadline = deadline.plusDays(period);
    }

    @Override
    public String toString() {
        String ret = "[D]" + super.toString() + " (by: " + deadline + ")";
        if (period > 1) {
            ret += " (recurs: Every " + period + " days)";
        } else if (period == 1) {
            ret += " (recurs: Every day)";
        }
        return ret;
    }
}
