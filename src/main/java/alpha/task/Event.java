package alpha.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_RESET = "\u001B[0m";
    protected String date;

    public Event(String description, String date, String taskType) {
        super(description, taskType);
        this.date = date;
    }
    public String getDate() {
        return this.date;
    }
    @Override
    public String toString() {
        return super.toString() + String.format(ANSI_RED + " (on: %s)", date + ANSI_RESET);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(obj instanceof Event) {
            Event e = (Event) obj;
            return (e.getDescription().equals(this.getDescription()) && e.getStatus().equals(this.getStatus()) && e.taskType.equals(this.taskType) && e.getDate().equals(this.getDate()));
        }
        return false;
    }
}
