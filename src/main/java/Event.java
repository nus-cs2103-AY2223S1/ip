import java.time.temporal.TemporalAccessor;

public class Event extends Task {
    private static final String DATE_TIME_SEPARATOR = " /at ";

    private final TemporalAccessor dateTime;

    public Event(String description, TemporalAccessor dateTime) {
        this(description, false, dateTime);
    }

    private Event(String description, boolean isDone, TemporalAccessor dateTime) {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    public static Event fromUserInput(String userInput) throws InvalidTaskFormatException {
        String[] splitUserInput = userInput.split(DATE_TIME_SEPARATOR, 2);
        if (splitUserInput.length < 2) {
            throw new InvalidTaskFormatException("No time was provided for this event.");
        }
        TemporalAccessor dateTime = DateTimeUtil.parseCompactDateTime(splitUserInput[1]);
        return new Event(splitUserInput[0], dateTime);
    }

    public static Event fromEncodedString(String encodedString) throws InvalidTaskDataException {
        String[] splitTaskData = encodedString.split("\\|");
        if (splitTaskData.length < 4) {
            throw new InvalidTaskDataException("The data for this event is not formatted correctly.");
        }
        String description = splitTaskData[2];
        boolean isDone = splitTaskData[1].equals("1");
        TemporalAccessor dateTime = DateTimeUtil.parseCompactDateTime(splitTaskData[3]);
        return new Event(description, isDone, dateTime);
    }

    @Override
    public String toEncodedString() {
        return String.format("E|%s|%s", super.toEncodedString(),
                DateTimeUtil.formatCompactDateTime(this.dateTime));
    }

    @Override
    public String toString() {
        return String.format("(E) %s (at: %s)", super.toString(),
                DateTimeUtil.formatPrettyDateTime(this.dateTime));
    }
}
