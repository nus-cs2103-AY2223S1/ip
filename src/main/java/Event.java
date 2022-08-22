import java.time.LocalDateTime;
import java.time.Month;

public class Event extends Task {
    private static final String type = "[E]";
    private LocalDateTime time;

    public Event(String name, int count, LocalDateTime time) throws MissingDescriptionException {
        super(name, count);
        this.time = time;
    }

    @Override
    public String toString() {
        String comp = this.completed
                ? "[X]"
                : "[ ]";
        int year = time.getYear();
        String month = time.getMonth().toString();
        int date = time.getDayOfMonth();
        int hour = time.getHour();
        int minute = time.getMinute();
        String dateString = date + " " + month + " " + year + " " + hour + ":" + minute;
        return String.format("%d." + type + comp + name + dateString, count);
    }

    @Override
    public String toStr() {
        String comp = this.completed
                ? "[X]"
                : "[ ]";
        int year = time.getYear();
        String month = time.getMonth().toString();
        int date = time.getDayOfMonth();
        int hour = time.getHour();
        int minute = time.getMinute();
        String dateString = date + " " + month + " " + year + " " + hour + ":" + minute;
        return type + comp + name + dateString;
    }
}
