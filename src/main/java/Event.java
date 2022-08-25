import java.time.LocalDateTime;
import java.util.Optional;

class Event extends Task {
    private static final String PREFIX = "at ";
    private static final String SPLIT = "/at ";

    private String period;
    private final Optional<LocalDateTime> dateTime;

    Event(String description, String period, Optional<LocalDateTime> dateTime) {
        super(description);
        this.period = period;
        this.dateTime = dateTime;
    }

    static Event createEvent(ParsedData data) throws DukeException {
        if (data.description.length() == 0)
            throw new EmptyDescriptionException("event");

        if (data.additionalInfo.length() == 0 || !data.additionalInfo.startsWith(PREFIX))
            throw new EmptyTimeException("event", SPLIT);
        String additonalInfo = data.additionalInfo.substring(3);
        return new Event(data.description, additonalInfo, Parser.strToDateTime(additonalInfo));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), period);
    }
}
