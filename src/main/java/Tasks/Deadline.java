/**
 * Creates a new deadline task from the deadline command
 */
package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime date;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh.mma");

    public Deadline(String msg, LocalDateTime date) {
        super(msg);
        this.date = date;
    }

    @Override
    public String getDateline() {
        return this.date.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return String.format("%s%s (by: %s)", "[D]", super.toString(),
                this.date.format(formatter));
    }
}
