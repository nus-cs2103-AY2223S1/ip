import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class EventTask extends Task {
    protected LocalDate event;

    EventTask(String action, boolean isDone, String event) throws DukeException {
        super(action, isDone, event);
        if (Objects.equals(event, "")) {
            throw new DukeException();
        }
        this.event = LocalDate.parse(event, DateTimeFormatter.ofPattern("dd/MM/yyyy"));;
    }

    EventTask(String action, String event) throws DukeException {
        this(action, false, event);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                event.format(DateTimeFormatter.ofPattern("E, MMM dd yyyy")) + ")";
    }
}
