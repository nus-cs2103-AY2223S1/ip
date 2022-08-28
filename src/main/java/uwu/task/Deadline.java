package uwu.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import uwu.exception.UwuException;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String by) throws UwuException {
        super(description);
        this.by = new UwuDateTime(by).getDateTime();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    @Override
    public String toStorageString() {
        String isDoneIndicator = super.isDone ? "1" : "0";
        String byString = by.toString().replaceAll("T", " ");
        return "D," + isDoneIndicator + "," + description.trim() + "," + byString;
    }
}