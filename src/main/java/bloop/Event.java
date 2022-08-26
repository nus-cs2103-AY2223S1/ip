package bloop;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private String at;
    private LocalDateTime dateTime;

    public Event(String task, String at) {
        super(task);
        this.at = at;
        this.dateTime = LocalDateTime.parse(at.trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy kkmm"));
    }

    protected String getBy() {
        return at;
    }

    protected String getDateTime() {
        return formatDateTime(dateTime);
    }

    protected char getType() {
        return 'E';
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + getDateTime() + ")";
    }
}
