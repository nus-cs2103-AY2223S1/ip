import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected final LocalDate time;

    public Event(String name, LocalDate time) {
        super(name);
        this.time = time;
    }

    public String toString() {
        return String.format("[%s]%s (at: %s)", this.getType(), super.toString(), this.getTime());
    }

    public String getTime() {
        return this.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    public String getType() {
        return "E";
    }
}