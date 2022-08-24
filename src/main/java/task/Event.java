package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate time;

    public Event(String name, LocalDate time) {
        super(name);
        this.time = time;
    }

    @Override
    public String stringify() {
        return "E##" + super.stringify() + "##" + this.time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
