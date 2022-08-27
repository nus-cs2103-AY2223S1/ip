package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static duke.DukeConstants.KEY_SEPARATOR;

public class Event extends Task {
    private LocalDate at;
    private String time;

    /**
     * Takes in a description and time for the task
     * @param description task description
     * @param at time of task
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
        this.time = "";
    }

    public Event(String description, LocalDate at, String time) {
        super(description);
        this.at = at;
        this.time = time;
    }

    @Override
    public String formatToSave() {
        int value;
        if(isDone) {
            value = 1;
        } else {
            value = 0;
        }
        return "E" + KEY_SEPARATOR + value + KEY_SEPARATOR + description + KEY_SEPARATOR + at + " " + time;
    }
    /**
     * Returns a String representation of the task
     * @return string
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " " + this.time + ")";
    }
}
