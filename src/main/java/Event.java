import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime time;
    protected String timeString;

    public Event(String description, String time, boolean isDone) {
        super(description, isDone);
        this.timeString = time;
        this.time = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    public String getTime() {
        int len = timeString.length();
        return timeString.substring(len - 4);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time.getDayOfWeek() + " " + time.getDayOfMonth() + " " +
                time.getMonth() + " " + time.getYear() + " " + this.getTime() + ")";
    }

    public String toStorageFormat() {
        char done = isDone ? '1' : '0';
        return "E" + " | " + done + " | " + this.description + " | " + this.timeString + "\n";
    }
}
