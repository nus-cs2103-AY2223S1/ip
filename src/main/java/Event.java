public class Event extends Task {
    private static final String TIME_SEPARATOR = " /at ";

    private final String time;

    private Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public static Event fromUserInput(String userInput) throws InvalidTaskFormatException {
        String[] splitUserInput = userInput.split(TIME_SEPARATOR, 2);
        if (splitUserInput.length < 2) {
            throw new InvalidTaskFormatException("No time was provided for this event.");
        }
        return new Event(splitUserInput[0], splitUserInput[1]);
    }

    @Override
    public String toString() {
        return String.format("(E) %s (at: %s)", super.toString(), this.time);
    }
}
