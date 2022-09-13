package yilia.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has takes place at a certain time.
 */
public class Event extends Task {
    private final LocalDate date;
    /**
     * Class constructor.
     */
    public Event(String content, String date) {
        super(content);
        this.date = LocalDate.parse(date);
    }
    /**
     * Class constructor specifying the content, whether it is done and the date.
     */
    public Event(String content, boolean isDone, String date) {
        super(content, isDone);
        this.date = LocalDate.parse(date);
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
    @Override
    public String parse() {
        return "E / " + (status() ? "1" : "0") + " / " + super.parse() + " / " + date;
    }
    /**
     * Checks if two events are the same.
     *
     * @param obj The other object to compare with.
     * @return A boolean value indicating if two tasks are the same.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Event)) {
            return false;
        }
        return super.equals(obj) && this.date.equals(((Event) obj).date);
    }
}
