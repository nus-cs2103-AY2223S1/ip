package duke;
import java.time.LocalDateTime;

/**
 * Task with a date and time at
 */
public class Event extends Task {
    private static final String type = "[E]";
    private LocalDateTime time;

    /**
     * Constructor for new event instance.
     *
     * @param name name of task.
     * @param time date and time in localdatetime type.
     * @throws MissingDescriptionException for missing name.
     */
    public Event(String name, LocalDateTime time) throws MissingDescriptionException {
        super(name);
        this.time = time;
    }

    /**
     * Returns string representation of task.
     *
     * @return string with type, completed or not, name, and date time(if applicable).
     */
    @Override
    public String toString() {
        String comp = this.isCompleted
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

    /**
     * Returns string representation of task to be written in text file.
     *
     * @return string representation to be written in text file.
     */
    @Override
    public String toData() {
        String type = "E";
        String completed = this.isCompleted ? "1" : "0";
        int year = time.getYear();
        int month = time.getMonthValue();
        int date = time.getDayOfMonth();
        int hour = time.getHour();
        int minute = time.getMinute();
        String result = type + "//" + completed + "//" + name + "//"
                + year + "-" + month + "-" + date + "//" + hour + ":" + minute;
        return result;
    }
}
