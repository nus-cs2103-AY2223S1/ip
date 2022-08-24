package yilia.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has takes place at a certain time.
 */
public class Event extends Task {
    private LocalDate date;
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
}
