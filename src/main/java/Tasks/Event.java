/**
 * Creates a new Event from the event command
 */
package Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate date;

    public Event(String msg, LocalDate date) {
        super(msg);
        this.date = date;
    }

    @Override
    public String getDateline() {
        return this.date.format(DateTimeFormatter.ofPattern("dd MMM uuuu"));
    }

    @Override
    public String toString() {
        return String.format("%s%s (at: %s)", "[E]", super.toString(), this.date.toString());
    }

}
