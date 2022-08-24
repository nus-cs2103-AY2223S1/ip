package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * String format for text file.
     *
     * @return string to be written into the text file.
     */
    @Override
    public String toStringFileFormat() {
        return "E | " + super.toStringFileFormat() + " | " + this.at;
    }

    /**
     * String representation of Deadline.
     *
     * @return string representation of Deadline.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at.format(DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm")) + ")";
    }
}
