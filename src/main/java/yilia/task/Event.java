package yilia.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate date;
    public Event(String content, String date) {
        super(content);
        this.date = LocalDate.parse(date);
    }
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
