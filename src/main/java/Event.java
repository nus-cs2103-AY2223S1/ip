import java.time.LocalDateTime;
import java.util.Optional;

class Event extends Task {
    private static final String PREFIX = "at ";
    private static final String SPLIT = "/at ";

    private String period;
    private final Optional<LocalDateTime> dateTime;

    Event(String description, String period) {
        super(description);
        this.period = period;
        this.dateTime = Parser.strToDateTime(period);
    }

    static Event createEvent(ParsedData data) throws DukeException {
        if (data.description.length() == 0)
            throw new EmptyDescriptionException("event");

        if (data.additionalInfo.length() == 0 || !data.additionalInfo.startsWith(PREFIX))
            throw new EmptyTimeException("event", SPLIT);
        return new Event(data.description, data.additionalInfo.substring(3));
    }

    static Event createEvent(String description, String period) throws CorruptedLineException {
        if (description.length() == 0 || period.length() == 0)
            throw new CorruptedLineException();

        return new Event(description, period);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), period);
    }

    @Override
    public ParsedData convertToParseData() {
        return new ParsedData(completed ? "Ec" : "Ex", description, period);
    }
}
