package duke.entities;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

import duke.enums.Messages;
import duke.exceptions.DukeException;

/**
 * Task with a description
 */
public class Task implements Comparable {
    private Boolean isComplete = false;
    private String description;
    /*deadline is set to min for tasks without deadline*/
    private LocalDateTime deadline = LocalDateTime.MIN;

    /**
     * Sets the description of the task
     * @param desc Description of the task
     */
    public Task(String desc) throws DukeException {
        if (desc == null) {
            throw new DukeException(Messages.ERROR_MISSING_PARAMETERS.toString());
        }
        this.description = desc;
    }

    /**
     * Flips the value isComplete
     */
    public void toggleComplete() {
        isComplete = !isComplete;
    }

    /**
     * Checks if the boolean is complete
     * @return true if completed else false
     */
    public boolean isDone() {
        return isComplete;
    }

    /**
     * Retrieves the description of the task
     * @return Description of task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for deadline
     * @return Deadline of Event
     */
    public String getDeadline() {
        LocalDateTime deadline = getLocalDateTime();
        int h = deadline.getHour();
        assert h <= 12 && h >= 0 : "m should be between 0 and 12";
        String hour = h < 9 ? "0" + h : Integer.toString(h);

        int m = deadline.getMinute();
        assert m <= 59 && m >= 0 : "m should be between 0 and 59";
        String minutes = m < 9 ? "0" + m : Integer.toString(m);

        String dayOfWeek = deadline.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        int d = deadline.getDayOfMonth();
        String dayOfMonth = d < 9 ? "0" + d : Integer.toString(d);
        String month = deadline.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        Integer year = deadline.getYear();
        return String.format(" < %s:%s %s %s %s %s >", hour, minutes, dayOfWeek, dayOfMonth, month, year);
    }

    /**
     * Returns deadline as a localDateTime object
     * @return deadline
     */
    public LocalDateTime getLocalDateTime() {
        return deadline;
    }

    public void setDeadline(LocalDateTime dt) {
        this.deadline = dt;
    }
    /**
     * Checks if the task contains the keyword
     * @param keyword keyword to search
     * @return true if the keyword is in else false
     */
    public boolean contains(String keyword) {
        return toString().contains(keyword);
    }
    @Override
    public String toString() {
        String marker = isComplete ? "[X] " : "[ ] ";
        return marker + description;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Task) { // Works for Deadline too since it is subclass
            return getLocalDateTime().compareTo(((Task) o).getLocalDateTime());
        }
        return -1; // always smaller when compared with non-Event objects
    }

}
