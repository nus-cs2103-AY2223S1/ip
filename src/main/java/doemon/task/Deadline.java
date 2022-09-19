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
            dueByDate = LocalDate.parse(dateTime[0],
                    DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT));
            if (dateTime.length == 2) {
                dueByTime = LocalTime.parse(dateTime[1],
                        DateTimeFormatter.ofPattern("HHmm").withResolverStyle(ResolverStyle.STRICT));
            }
        } catch (DateTimeParseException e) {
            dueByStr = dueBy;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSaveString() {
        String date = dueByDate != null
                      ? dueByDate.toString()
                      : dueByStr;
        String time = dueByTime != null
                      ? String.format(" %s", dueByTime.format(DateTimeFormatter.ofPattern(("HHmm"))))
                      : "";
        return String.format("D | %s | %s%s", super.getSaveString(), date, time);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String date = dueByDate != null
                      ? dueByDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                      : dueByStr;
        String time = dueByTime != null
                      ? String.format(" %s", dueByTime.format(DateTimeFormatter.ofPattern("K:mma")))
                      : "";
        return String.format("[D]%s (by: %s%s)", super.toString(), date, time);
    }
}
