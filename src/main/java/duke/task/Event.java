package duke.task;

import duke.exception.DukeException;

import java.time.format.DateTimeFormatter;

public class Event extends Task {

    /** Event date of the task */
    private String date;

    public Event(String description, String at, boolean dateIsFormatted) throws DukeException {
        super(description);
        if (dateIsFormatted == true) {
            this.date = at;
        } else {
            this.date = getLocalDateTime(at, "event", "/at").format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm a"));
        }
    }

    @Override
    public String getDate() {
        return this.date;
    }

    /**
     * Returns String representation of the event details.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}
