package duke;
import java.time.LocalDateTime;

public class Event extends Task {
    private static final String type = "[E]";
    private LocalDateTime time;

    public Event(String name,LocalDateTime time) throws MissingDescriptionException {
        super(name);
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
        return type + comp + name + dateString;
    }

    @Override
    public String toData() {
        String type = "E";
        String completed = this.completed ? "1" : "0";
        int year = time.getYear();
        int month = time.getMonthValue();
        int date = time.getDayOfMonth();
        int hour = time.getHour();
        int minute = time.getMinute();
        String result = type + "//" + completed +"//" + name + "//" +
                year + "-" + month + "-" + date + "//" + hour + ":" + minute;
        return result;
    }
}
