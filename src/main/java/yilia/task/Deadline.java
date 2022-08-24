package yilia.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate date;
    public Deadline(String content, String date) {
        super(content);
        this.date = LocalDate.parse(date);
    }
    public Deadline(String content, boolean isDone, String date) {
        super(content, isDone);
        this.date = LocalDate.parse(date);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
    @Override
    public String parse() {
        return "D / " + (status() ? "1" : "0") + " / " + super.parse() + " / " + date;
    }
}
