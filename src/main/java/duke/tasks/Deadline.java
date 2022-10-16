package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;



public class Deadline extends Task {

    protected final LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    /**
     * Formats the time to a string.
     *
     * @param time time of the Deadline.
     * @return String notation of the time.
     */
    public String formatTime(LocalDate time) {
        return time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }


    /**
     * Shows the Deadline task description and the date it is due by.
     *
     * @return String with the Deadline task description and date it is due by.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatTime(by) + ")";
    }

    @Override
    public String saveTask() {
        return String.format("D | %s | %s", super.saveTask(), by);
    }
}
