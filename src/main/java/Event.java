import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime date;
    private final static DateTimeParser PARSER = new DateTimeParser();

    public Event(String taskName, String date) {
        super(taskName);
        this.date = PARSER.getDateTime(date);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + PARSER.getDateTimeString(date) + ")";
    }
}
