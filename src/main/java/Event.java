import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime time;
    public Event(String content, LocalDateTime time) {
        super(content);
        this.time = time;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String timeString = this.time.format(formatter);
        return this.isDone() ? "[E][X] " + this.getContent() + " at " + this.time
                :"[E][ ] " + this.getContent() + " at " + timeString;
    }
}
