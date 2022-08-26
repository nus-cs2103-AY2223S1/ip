package duke.entities;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

import duke.exceptions.DukeException;

/**
 * Event with a description and a deadline
 */
public class Event extends Todo {
    private final LocalDateTime deadline;

    /**
     * Initialises the event with desc and deadline
     * @param desc describes the event
     * @param deadline when the event is supposed to be completed
     * @throws DukeException when there is an error
     */
    public Event(String desc, LocalDateTime deadline) throws DukeException {
        super(desc);
        this.deadline = deadline;
    }

    /**
     * Getter for deadline
     * @return Deadline of Event
     */
    public String getDeadline() {
        int h = deadline.getHour();
        String hour = h < 9 ? "0" + h : Integer.toString(h);
        int m = deadline.getMinute();
        String minutes = m < 9 ? "0" + m : Integer.toString(m);
        String dayOfWeek = deadline.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        int d = deadline.getDayOfMonth();
        String dayOfMonth = d < 9 ? "0" + d : Integer.toString(d);
        String month = deadline.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        Integer year = deadline.getYear();
        return String.format(" < %s:%s %s %s %s %s >", hour, minutes, dayOfWeek, dayOfMonth, month, year);
    }

    @Override
    public String toString() {
        String marker = isDone() ? "[X] " : "[ ] ";
        return "[E]" + marker + getDescription() + getDeadline();
    }
}
