package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate time;
    private final char type = 'E';

    public Event(String taskname, LocalDate time) {
        super(taskname);
        this.time = time;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy");
        return String.format("[%c] %s (at: %s)", this.type, super.toString(), this.time.format(formatter));
    }

    @Override
    public String toSavedString() { //internal representation in save file
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-d");
        return "" + this.type + "#" + super.toSavedString() + "#" + this.time.format(formatter);
    }
}
