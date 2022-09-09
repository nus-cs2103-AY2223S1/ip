package doemon.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Event task with date/time that it will occur.
 */
public class Event extends Task {
    /** String that describes the date/time of the event. */
    private String dueAtStr;
    /** LocalDate instance of valid date input. */
    private LocalDate dueAtDate = null;
    /** LocalTime instance of valid time input. */
    private LocalTime dueAtTime = null;

    /**
     * Constructor for Event.
     *
     * @param description Description of the event.
     * @param dueAt Date or time of the event.
     */
    public Event(String description, String dueAt) {
        super(description);
        try {
            String[] dateTime = dueAt.split(" ");
            this.dueAtDate = LocalDate.parse(dateTime[0],
                    DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT));
            if (dateTime.length == 2) {
                this.dueAtTime = LocalTime.parse(dateTime[1],
                        DateTimeFormatter.ofPattern("HHmm").withResolverStyle(ResolverStyle.STRICT));
            }
        } catch (DateTimeParseException e) {
            this.dueAtStr = dueAt;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSaveString() {
        String date = this.dueAtDate != null
                      ? this.dueAtDate.toString()
                      : this.dueAtStr;
        String time = this.dueAtTime != null
                      ? String.format(" %s", this.dueAtTime.format(DateTimeFormatter.ofPattern(("HHmm"))))
                      : "";
        return String.format("E | %s | %s%s", super.getSaveString(), date, time);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String date = this.dueAtDate != null
                      ? this.dueAtDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                      : this.dueAtStr;
        String time = this.dueAtDate != null && this.dueAtTime != null
                      ? String.format(" %s", this.dueAtTime.format(DateTimeFormatter.ofPattern("K:mma")))
                      : "";
        return String.format("[E]%s (at: %s%s)", super.toString(), date, time);
    }
}
