package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    protected String description;
    protected boolean isDone;
    protected LocalDateTime timing;

    /**
     * Constructor for Events task.
     *
     * @param description Description of event.
     * @param timing Time at which event is to take place.
     */
    public Events(String description, String timing) {
        super(description);
        this.description = description;
        this.isDone = false;
        this.timing = LocalDateTime.parse(timing.strip(), DateTimeFormatter.ofPattern("dd/MM/yyyy k:mm"));
    }

    /**
     * Returns string representation of the event task.
     *
     * @return Task string for list representation.
     */
    public String toString() {
        String result = "[E]" + "[" + getStatusIcon() + "] " + this.description.strip() + " (at: "
                + this.timing.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + ")";
        return result;
    }

    /**
     * Returns string representation of the event task to be written to file.
     *
     * @return Task string for saving in hard drive.
     */
    public String fileString() {
        String write = "E / " + (isDone ? "1 / " : "0 / ") + this.description.strip() + " / "
                + this.timing.format(DateTimeFormatter.ofPattern("dd/MM/yyyy k:mm"));
        return write;
    }
}
