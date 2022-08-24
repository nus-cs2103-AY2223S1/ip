package duke.entities;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

import duke.exceptions.DukeException;

public class Event extends Todo {
    private LocalDateTime deadline;

    public Event(String desc, LocalDateTime deadline) throws DukeException {
        super(desc);
        this.deadline = deadline;
    }

    /**
     * Getter for deadline
     * 
     * @return Deadline of Event
     */
    public String getDeadline() {
        int h = deadline.getHour();
        String hour = h < 9 ? "0" + Integer.toString(h) : Integer.toString(h);
        int m = deadline.getMinute();
        String minutes = m < 9 ? "0" + Integer.toString(m) : Integer.toString(m);
        String day_of_week = deadline.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        int d = deadline.getDayOfMonth();
        String day_of_month = d < 9 ? "0" + Integer.toString(d) : Integer.toString(d);
        String month = deadline.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        Integer year = deadline.getYear();
        return String.format(" < %s:%s %s %s %s %s >", hour, minutes, day_of_week, day_of_month, month, year);
    }

    @Override
    public String toString() {
        String marker = isDone() ? "[X] " : "[ ] ";
        return "[E]" + marker + getDescription() + getDeadline();
    }
}
