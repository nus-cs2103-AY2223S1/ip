import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDateTime time;

    public Event(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    @Override
    public String getStatus() {
        if(this.isDone) {
            return "[E][X]";
        } else {
            return "[E][ ]";
        }
    }
    @Override
    public String toString() {
        return this.getStatus() + " " + this.description + " (at: " +
                this.time.format(DateTimeFormatter.ofPattern("hh:mm a 'on' dd/MM/yyyy")) + ")";
    }
}
