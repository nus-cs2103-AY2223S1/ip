import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected final String TAG = "[E]";
    protected static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    protected LocalDateTime end;

    public Event(String descriptor, String duration) {
        super(descriptor);
        this.time = LocalDateTime.parse(duration.substring(0,16), DTF);
        this.end = LocalDateTime.parse(duration.substring(0,10) + " " + duration.substring(17), DTF);
    }

    @Override
    public String toString() {
        return TAG + super.toString() + " (at: " +
                this.time.format(DTF) + " to " +
                this.end.format(DTF) + ")";
    }
}
