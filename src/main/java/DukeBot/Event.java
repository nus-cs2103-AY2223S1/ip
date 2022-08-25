package DukeBot;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.time.LocalDate;

public class Event extends Task {

    private LocalDate time;

    public Event(String description, String time) throws DukeException {
        super(description);
        try {
            this.time = LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new DukeException("DateTime not in yyyy-MM-dd format");
        }
    }

    /**
     * Get the type of Task.
     *
     * @return "E" indicating Event.
     */
    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * Get the time of the Event.
     *
     * @return timing The time of the Event.
     */
    @Override
    public String getTime() {
        return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Get the String representation of an Event.
     *
     * @return String representation of an Event.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (at: "
                + this.time.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + ")", this.getTaskType(), super.toString());
    }
}
