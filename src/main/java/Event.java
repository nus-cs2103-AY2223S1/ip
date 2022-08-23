import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate atDate;
    private LocalDateTime atDateTime;
    private boolean hasTime;

    public Event(String desc, LocalDate atDate) throws MissingDescriptionException {
        super(desc);
        if (desc.isBlank()) {
            throw new MissingDescriptionException(Duke.commandGuide("event", Command.EVENT));
        }
        this.atDate = atDate;
        this.hasTime = false;
    }

    public Event(String desc, LocalDateTime atDateTime) throws MissingDescriptionException {
        super(desc);
        if (desc.isBlank()) {
            throw new MissingDescriptionException(Duke.commandGuide("event", Command.EVENT));
        }
        this.atDateTime = atDateTime;
        this.hasTime = true;
    }

    private String formatBy() {
        if (this.hasTime) {
            String formattedDateTime = this.atDateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a"));
            return formattedDateTime;
        } else {
            String formattedDate = this.atDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
            return formattedDate;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.formatBy() + ")";
    }
}
