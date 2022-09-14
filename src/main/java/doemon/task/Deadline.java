package doemon.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Deadline task with due date and/or time.
 */
public class Deadline extends Task {
    /** String describing the due date or time of the deadline. */
    private String dueByStr;
    /** LocalDate instance of entered date. */
    private LocalDate dueByDate = null;
    /** LocalTime instance of entered time. */
    private LocalTime dueByTime = null;

    /**
     * Constructor for Deadline.
     *
     * @param description Description of the deadline.
     * @param dueBy Due date and/or time of the deadline.
     */
    public Deadline(String description, String dueBy) {
        super(description);
        try {
            String[] dateTime = dueBy.split(" ");
            this.dueByDate = LocalDate.parse(dateTime[0],
                    DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT));
            if (dateTime.length == 2) {
                this.dueByTime = LocalTime.parse(dateTime[1],
                        DateTimeFormatter.ofPattern("HHmm").withResolverStyle(ResolverStyle.STRICT));
            }
        } catch (DateTimeParseException e) {
            this.dueByStr = dueBy;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSaveString() {
        String date = this.dueByDate != null
                      ? this.dueByDate.toString()
                      : this.dueByStr;
        String time = this.dueByTime != null
                      ? String.format(" %s", this.dueByTime.format(DateTimeFormatter.ofPattern(("HHmm"))))
                      : "";
        return String.format("D | %s | %s%s", super.getSaveString(), date, time);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String date = this.dueByDate != null
                      ? this.dueByDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                      : this.dueByStr;
        String time = this.dueByTime != null
                      ? String.format(" %s", this.dueByTime.format(DateTimeFormatter.ofPattern("K:mma")))
                      : "";
        return String.format("[D]%s (by: %s%s)", super.toString(), date, time);
    }
}
