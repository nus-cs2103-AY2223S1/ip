import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDateTime at;

    public Event(String description, boolean done, String at) {
        super(description, done);
        this.at = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public char getType()
    {
        return 'E';
    }

    @Override
    public String getDetail()
    {
        return at.toString();
    }
}