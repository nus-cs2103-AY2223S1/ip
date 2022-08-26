package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime dateTime;

    public Deadline(LocalDateTime dateTime, String description) {
        super(description, "D");
        this.dateTime = dateTime;
    }

    public Deadline(LocalDateTime dateTime, String description, boolean isDone) {
        super(description, "D", isDone);
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return " (by: " +
                this.dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy', ' hh:mm a")) + ")";
    }

    public LocalDateTime getRawDateTime() {
        return this.dateTime;
    }

    @Override
    public String printText() {
        return super.printText() + " | " + this.getDateTime();
    }

    @Override
    public String toString() {
        return "[" + this.getCode() + "]" + super.toString() + this.getDateTime();
    }
}