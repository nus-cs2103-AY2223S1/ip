import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Event extends Task{
    private String at;
    private LocalDate eventDate;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.eventDate = LocalDate.parse(at);
    }

    @Override
    public String toString(){
        return String.format("[E]" + super.toString() + " (at: %s)",
                eventDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
