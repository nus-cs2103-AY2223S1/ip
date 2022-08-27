package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Event class represents a task
 * with a specific date or time.
 */
public class Event extends Task{
    private String eventAt;
    private LocalDate eventDate;

    /**
     * Constructs a Event object
     * @param description description for the event.
     * @param eventDate string that represents time of event.
     */
    public Event(String description, String eventDate) {
        super(description);
        try {
            LocalDateTime localDateTime;
            localDateTime = LocalDateTime.parse(eventDate, DateTimeFormatter.ofPattern("yyyy-M-d HHmm"));
            this.eventDate = localDateTime.toLocalDate();
            this.eventAt = localDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma"));
        } catch (DateTimeParseException e) {
            try {
                String[] strings = eventDate.split(" ");
                this.eventDate = LocalDate.parse(strings[0]);
                this.eventAt = this.eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                if (strings.length > 1) {
                    this.eventAt += " " + eventDate.substring(eventDate.indexOf(" ") + 1);
                }
            } catch (DateTimeParseException e2) {
                this.eventAt = eventDate;
            }
        }
    }

    @Override
    public boolean compareDate(LocalDate date) {
        return date.equals(this.eventDate);
    }

    /**
     * Constructor for Event when loaded from Storage.
     * @param i Mark status.
     * @param description Specified Event description.
     * @param eventAt Specified Event date.
     */
    public Event(int i, String description, String eventAt) {
        super(description);
        this.eventAt = eventAt;
        if (i == 1) {
            this.markDone();
        }
    }

    @Override
    public String toStore() {
        return "E" + " | " + super.toStore() + " | " + this.eventAt;
    }


    /**
     * Overriding method of toString() for duke.task.Event.
     * @return the string representing duke.task.Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventAt + ")";
    }
}
