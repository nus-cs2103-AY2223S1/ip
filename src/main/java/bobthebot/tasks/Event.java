package bobthebot.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class representing events, a type of task.
 */
public class Event extends Task {
    private String at;

    /**
     * Constructs an instance of an event.
     *
     * @param description of the event.
     * @param at the date and time of the event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at.trim();
    }

    /**
     * Changes the format of the date from YYYY-MM-DD to MMM D YYYY
     * Eg. Method will change 2022-12-12 to Dec 12 2022.
     *
     * @param at Date to be changed.
     * @return String in format MMM D YYYY.
     */
    public String changeAtFormat(String at) {
        // split the date and the time
        String[] splitDeadline = at.split(" ");
        String givenDate = splitDeadline[0].trim();
        LocalDate outputDate = LocalDate.parse(givenDate);

        String time = splitDeadline[1].trim();
        String date = outputDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        return date + ", " + time;
    }

    /**
     * Returns a String representation of an event.
     *
     * @return String representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.changeAtFormat(at) + ")";
    }

    /**
     * Converts an event to a format for storage.
     *
     * @return String representation of a event for storage.
     */
    @Override
    public String toStorageFormat() {
        int done = isDone ? 1 : 0;
        String res = String.format("D | %d | %s | %s", done, taskName, at);
        return res;
    }
}
