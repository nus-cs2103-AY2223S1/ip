import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private final String tag = "[E]";
    private final LocalDate date;

    public Event(String name, String dateString) {
        super(name);
        this.date = LocalDate.parse(dateString);
    }


    @Override
    public String toString() {
        return tag + "[" + this.getStatusIcon()  + "] " + this.getTaskName() + "(" + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
