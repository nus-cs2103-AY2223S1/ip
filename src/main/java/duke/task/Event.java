package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected String at;
    protected LocalDate date;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        try {
            this.date = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            this.date = null;
        }
    }

    @Override
    public String getStringToSave() {
        return this.isDone
                ? "E | 1 | " + description + " | " + at
                : "E | 0 | " + description + " | " + at;
    }

    @Override
    public String toString() {
        return date == null
                ? "[E]" + super.toString() + " (at: " + at + ")"
                : "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
