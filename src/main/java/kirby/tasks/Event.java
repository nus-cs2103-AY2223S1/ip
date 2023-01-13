package kirby.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import kirby.time.HandleTime;

/**
 * Event class contains information of an Event task.
 */
public class Event extends Task {
    protected String at;
    private LocalDate localDate = null;

    /**
     * Constructor for the class Event.
     *
     * @param description Description of the task.
     * @param at Argument of the task which contains the event date.
     */
    public Event(String description, String at, boolean isDone) {
        super(description);
        this.at = at;
        if (HandleTime.isValidDate(at)) {
            this.localDate = LocalDate.parse(at);
        }
        if (isDone) {
            this.setCompleted();
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
            return new StringBuilder().append("[E] ").append(this.getStatusIcon()).append(" ").append(this.description)
                    .append(" (at: ").append(localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy)"))).toString();
        } else {
            return "[E] " + this.getStatusIcon() + " " + this.description + " (at: " + at + ")";
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toFileOutput() {
        return "kirby.tasks.Event~" + this.description + "~" + this.at + "~" + this.getStatusIcon();
    }
}
