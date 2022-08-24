package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private static final DateTimeFormatter ACCEPT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TO = DateTimeFormatter.ofPattern("dd MMM yyyy");
    protected LocalDate at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at, ACCEPT);
    }

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " AT " + this.at.format(TO);
    }
}

