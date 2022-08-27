package kirby.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import kirby.HandleTime;

/**
 * The kirby.Event class contains information of an kirby.Event task.
 * @author Sheryl-Lynn Tan (G11)
 */
public class Event extends Task {
    protected String at;
    private LocalDate localDate  = null;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        if (HandleTime.isValidDate(at)) {
            this.localDate = LocalDate.parse(at);
        }
    }

    @Override
    public int[] getDate() {
        return HandleTime.fromStringToDate(at);
    }

    @Override
    public String toString() {
        if (localDate != null) {
            return "[E] " + this.getStatusIcon() + " " + this.description + " (at: " + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy)"));
        } else {
            return "[E] " + this.getStatusIcon() + " " + this.description + " (at: " + at + ")";
        }
    }

    @Override
    public String toFileOutput() {
        return "kirby.Event~" + this.description + "~" + this.at;
    }
}