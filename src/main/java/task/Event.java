package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDate date = null;
    private String time = null;
    private static final String TYPE = "[E]";

    public Event(String name, String date) {
        super(name);
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            this.time = date;
        }
    }

    public Event(String name, String date, boolean isDone) {
        super(name, isDone);
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            this.time = date;
        }
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String toString() {
        if (time == null) {
            return TYPE + super.toString() + " (on: " +
                    date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return TYPE + super.toString() + " (on: " + time + ")";
        }
    }
}
