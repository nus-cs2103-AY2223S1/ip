import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends ListObject{

    String eventTime;

    public Event(String task, int status) {
        super(task, status);
    }

    public Event(String task, int status, String eventTime) {
        super(task, status);
        this.eventTime = eventTime;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + eventTime + ")";
    }
}
