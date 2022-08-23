package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String at;
    protected LocalDate start_date;
    protected LocalDate end_date;
    protected LocalTime start_time;
    protected LocalTime end_time;

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
        this.start_date = LocalDate.parse(split[0]);
        this.start_time = LocalTime.parse(split[1]);
        this.end_date = LocalDate.parse(split[2]);
        this.end_time = LocalTime.parse(split[3]);
    }

    @Override
    public String toString() {
        String start_d = start_date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String end_d = end_date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        return "[E]" + super.toString() + " (at: " + start_d + " " + start_time + " - "
                + end_d + " " + end_time + ")";
    }

    @Override
    public String toSaveData() {
        return "E" + " | " + super.toSaveData() + " | " + at;
    }
}
