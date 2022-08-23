import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static final String TASK_SYMBOL = "E";
    private static final String OUTPUT_FORMAT = "d MMM yyyy";
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", Event.TASK_SYMBOL, super.toString(), this.getFormattedDate());
    }

    @Override
    public String getType() {
        return Event.TASK_SYMBOL;
    }

    @Override
    public String getDate() {
        return this.at;
    }

    private String getFormattedDate() {
        LocalDate date = Parser.parseDate(this.at);
        return date.format(DateTimeFormatter.ofPattern(OUTPUT_FORMAT));
    }
}
