package kirby.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import kirby.HandleTime;

/**
 * Event class contains information of an Event task.
 */
public class Event extends Task {
    protected String at;
    private LocalDate localDate  = null;

    /**
     * Constructor for the class Event.
     *
     * @param description description of the task.
     * @param at the argument of the task which contains the event date.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        if (HandleTime.isValidDate(at)) {
            this.localDate = LocalDate.parse(at);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getDate() {
        return HandleTime.fromStringToDate(at);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        if (localDate != null) {
            return "[E] " + this.getStatusIcon() + " " + this.description + " (at: " + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy)"));
        } else {
            return "[E] " + this.getStatusIcon() + " " + this.description + " (at: " + at + ")";
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toFileOutput() {
        return "kirby.Event~" + this.description + "~" + this.at;
    }
}