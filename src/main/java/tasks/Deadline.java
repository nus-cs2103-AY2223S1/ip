package tasks;

import utils.DateTime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final DateTimeFormatter formatter = DateTime.formatter;
    private final LocalDateTime time;

    public Deadline(String description, String time) {
        super(description);
        this.time = LocalDateTime.parse(time, formatter);
    }

    public Deadline(boolean isMarked, String description, String time) {
        super(isMarked, description);
        this.time = LocalDateTime.parse(time);
    }

    @Override
    public String dbRepresentation() {
        return String.join("|", "D", Boolean.toString(isMarked),  description, time.toString());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.time.format(formatter) + ")";
    }
}
