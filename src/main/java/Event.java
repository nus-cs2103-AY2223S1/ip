import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected ZonedDateTime at;

    public Event(String description, ZonedDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public char getType() {
        return 'E';
    }

    @Override
    public String toString() {
        return String.format(
                "[E]%s (at: %s)",
                super.toString(),
                this.at.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mma"))
        );
    }
}
