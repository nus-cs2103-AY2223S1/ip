public class Event extends Task {
    private static final String TIME_SEPARATOR = " /at ";

    private final String time;

    public Event(String userInput) throws InvalidTaskFormatException {
        super(extractDescription(userInput));
        this.time = extractTime(userInput);
    }

    private static String extractDescription(String userInput) {
        return userInput.split(TIME_SEPARATOR)[0];
    }

    private static String extractTime(String userInput) throws InvalidTaskFormatException {
        String[] splitUserInput = userInput.split(TIME_SEPARATOR, 2);
        if (splitUserInput.length < 2) {
            throw new InvalidTaskFormatException("No time was provided for this event.");
        }
        return splitUserInput[1];
    }

    @Override
    public String toString() {
        return String.format("(E) %s (at: %s)", super.toString(), this.time);
    }
}
