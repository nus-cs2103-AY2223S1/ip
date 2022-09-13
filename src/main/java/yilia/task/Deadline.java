package yilia.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a deadline.
 */
public class Deadline extends Task {
    private final LocalDate date;
    /**
     * Class constructor specifying the content and date.
     */
    public Deadline(String content, String date) {
        super(content);
        this.date = LocalDate.parse(date);
    }
    /**
     * Class constructor specifying the content, whether it is done and date.
     */
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
    /**
     * Checks if two deadlines are the same.
     *
     * @param obj The other object to compare with.
     * @return A boolean value indicating if two tasks are the same.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Deadline)) {
            return false;
        }
        return super.equals(obj) && this.date.equals(((Deadline) obj).date);
    }
}
