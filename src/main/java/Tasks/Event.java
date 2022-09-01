/**
 * Creates a new Event from the event command
 */
package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime date;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh.mma");

    public Event(String msg, LocalDateTime date) {
        super(msg);
        this.date = date;
    }

    @Override
    public String getDateline() {
        return this.date.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return String.format("%s%s (at: %s)", "[E]",
                super.toString(), this.date.format(formatter));
    }

}
