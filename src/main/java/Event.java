public class Event extends Task {
    private static final String TIME_SEPARATOR = " /at ";

    private final String time;

    private Event(String description, String time) {
        this(description, false, time);
    }

    private Event(String description, boolean isDone, String time) {
        super(description, isDone);
        this.time = time;
    }

    public static Event fromUserInput(String userInput) throws InvalidTaskFormatException {
        String[] splitUserInput = userInput.split(TIME_SEPARATOR, 2);
        if (splitUserInput.length < 2) {
            throw new InvalidTaskFormatException("No time was provided for this event.");
        }
        return new Event(splitUserInput[0], splitUserInput[1]);
    }

    public static Event fromEncodedString(String encodedString) throws InvalidTaskDataException {
        String[] splitTaskData = encodedString.split("\\|");
        if (splitTaskData.length < 4) {
            throw new InvalidTaskDataException("The data for this event is not formatted correctly.");
        }
        String description = splitTaskData[2];
        boolean isDone = splitTaskData[1].equals("1");
        String time = splitTaskData[3];
        return new Event(description, isDone, time);
    }

    @Override
    public String toEncodedString() {
        return String.format("E|%s|%s", super.toEncodedString(), this.time);
    }

    @Override
    public String toString() {
        return String.format("(E) %s (at: %s)", super.toString(), this.time);
    }
}
