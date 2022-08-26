package bloop;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    private String by;
    private LocalDateTime dateTime;

    public Deadline(String task, String by) {
        super(task);
        this.by = by;
        this.dateTime = LocalDateTime.parse(by.trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy kkmm"));;
    }

    protected String getDateTime() {
        return formatDateTime(dateTime);
    }

    protected String getBy() {
        return by;
    }

    protected char getType() {
        return 'D';
    }

    public String toString() {
        return "[D]" + super.toString() + "(by: " + getDateTime() + ")";
    }
}
