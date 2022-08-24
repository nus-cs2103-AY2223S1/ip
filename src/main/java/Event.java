import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime timing;

    public Event(String taskDescription, String timing) throws UnrecognisedDateTimeException {
        super(taskDescription);
        try {
            //Alter the timing so that it can be properly added in.
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddHHmm");
            this.timing = LocalDateTime.parse(timing, formatter);
        } catch (DateTimeParseException e) {
            throw new UnrecognisedDateTimeException();
        }
    }

    /**
     * Format of parsing:
     * Type of task # status of task # description # timing
     * @return String that is in the parsing format.
     */
    @Override
    public String parse() {
        return String.format("E # %s # %s # %s", super.getStatusIcon(), super.getTaskDescription(), timing);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                timing.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }
}
