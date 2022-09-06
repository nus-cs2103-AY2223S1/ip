package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task implements Dated {
    private LocalDate date;

    public Event(String item, String dateString) {
        super(item);
        this.date = LocalDate.parse(dateString);
    }

    private String dateFormat(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public boolean isBetween(LocalDate start, LocalDate end) {
        return (this.date.isAfter(start) && this.date.isBefore(end));
    }

    @Override
    public boolean isDated() {
        return true;
    }

    /**
     * Returns String representation of an Event object
     * @return String representation
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " at: " + dateFormat(date);
    }
}
