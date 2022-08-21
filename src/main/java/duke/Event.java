package duke;

import java.time.LocalDate;
import java.time.DateTimeException;

public class Event extends Task {

    protected String when;
    protected LocalDate whenDate;

    public Event(String description, String when) {
        super(description);
        this.when = when;
        try {
            whenDate = LocalDate.parse(this.when);
        } catch (DateTimeException e) {

        }
    }

    @Override
    public String toString() {
        if (whenDate != null) {
            return "[E]" + super.toString() + " (at: " + whenDate.toString() + ")";
        } else {
            return "[E]" + super.toString() + " (at: " + when + ")";
        }
    }
}
