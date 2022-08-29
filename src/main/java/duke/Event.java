package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Event task that contains a duration.
 */
public class Event extends Task {

    private String at;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Constructor for an Event task
     *
     * @param description Description of the event.
     * @param at Start and end time for the event in a String.
     * @throws DateTimeException Thrown if the time format is wrong.
     */
    public Event(String description, String at) throws DateTimeException {
        super(description);
        this.at = at;
        String[] split = at.split(" ");
        if (split.length < 4) {
            throw new DateTimeException("Missing time/date");
        }
        this.startDate = LocalDate.parse(split[0]);
        this.startTime = LocalTime.parse(split[1]);
        this.endDate = LocalDate.parse(split[2]);
        this.endTime = LocalTime.parse(split[3]);
    }

    @Override
    public String toString() {
        String startDateMessage = startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String endDateMessage = endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        return "[E]" + super.toString() + " (at: " + startDateMessage + " " + startTime + " - "
                + endDateMessage + " " + endTime + ")";
    }

    @Override
    public String toSaveData() {
        return "E" + " | " + super.toSaveData() + " | " + at;
    }
}
