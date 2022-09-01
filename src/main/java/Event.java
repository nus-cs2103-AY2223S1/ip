import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Event extends Task {
    /**
     * A string the describes the date/time of the event.
     */
    private String dueAtStr;
    private LocalDate dueAtDate = null;
    private LocalTime dueAtTime = null;

    /**
     * Constructor for a deadline.
     * @param description the description of the deadline
     * @param dueAt the date/time of the event
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
     * Returns a string representation of the event.
     * @return a string representing the event
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
