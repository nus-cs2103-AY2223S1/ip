import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    LocalDate time;

    Event(String name, String time) {
        super(name);
        this.time = LocalDate.parse(time);
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at: %s)", super.toString(), time.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
