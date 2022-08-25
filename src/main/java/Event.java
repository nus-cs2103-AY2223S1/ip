import java.time.LocalDate;

public class Event extends Task{
    protected LocalDate at;

    public Event(String description, String at) {
        super(description);
        this.at = DateTimeConverter.formatDate(at.substring(1));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + DateTimeConverter.formatString(at) + ")";
    }
}
