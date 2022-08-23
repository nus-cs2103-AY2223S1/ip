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
        Integer hour = deadline.getHour();
        Integer minutes = deadline.getMinute();
        String day_of_week = deadline.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        Integer day_of_month = deadline.getDayOfMonth();
        String month = deadline.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        return String.format(" < %s:%s %s, %s %s > ", hour, minutes, day_of_week, day_of_month, month);
    }

    @Override
    public String toString() {
        String marker = isDone() ? "[X] " : "[ ] ";
        return "[E]" + marker + getDescription() + getDeadline();
    }
}
