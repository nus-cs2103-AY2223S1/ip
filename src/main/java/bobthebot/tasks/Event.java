package bobthebot.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class representing events, a type of task.
 */
public class Event extends Task {
    private String eventDate;

    /**
     * Constructs an instance of an event.
     *
     * @param description of the event.
     * @param eventDate the date and time of the event.
     */
    public Event(String description, String eventDate) {
        super(description);
        this.eventDate = eventDate.trim();
    }

    /**
     * Changes the format of the date from YYYY-MM-DD to MMM D YYYY
     * Eg. Method will change 2022-12-12 to Dec 12 2022.
     *
     * @return String in format MMM D YYYY.
     */
    private String changeEventDateFormat() {
        // split the date and the time
        String[] splitDeadline = eventDate.split(" ");
        String givenDate = splitDeadline[0].trim();
        LocalDate outputDate = LocalDate.parse(givenDate);

        String time = splitDeadline[1].trim();
        String date = outputDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        return date + ", " + time;
    }

    /**
     * Checks if the event is within a week away. Returns True if the event is within
     *      a week away and False otherwise.
     *
     * @return Boolean representing if a event is within a week away.
     */
    public Boolean isWithinWeekOfEvent() {
        String[] splitEvent = eventDate.split(" ");
        String eventDate = splitEvent[0].trim();
        LocalDate formattedEventDate = LocalDate.parse(eventDate);

        LocalDate today = LocalDate.now();
        LocalDate oneWeekLater = today.plusWeeks(1);

        return formattedEventDate.isBefore(oneWeekLater);
    }

    /**
     * Returns a String representation of an event.
     *
     * @return String representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.changeEventDateFormat() + ")";
    }

    /**
     * Converts an event to a format for storage.
     *
     * @return String representation of a event for storage.
     */
    @Override
    public String toStorageFormat() {
        int doneStatus = isDone ? 1 : 0;
        String eventString = String.format("E | %d | %s | %s", doneStatus, taskName, eventDate);
        return eventString;
    }
}
