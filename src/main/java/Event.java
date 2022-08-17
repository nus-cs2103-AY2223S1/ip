public class Event extends Task {
    private static final String TIME_SEPARATOR = " /at ";

    private final String time;

    public Event(String userInput) {
        super(extractDescription(userInput));
        this.time = extractTime(userInput);
    }

    private static String extractDescription(String userInput) {
        return userInput.split(TIME_SEPARATOR)[0];
    }

    private static String extractTime(String userInput) {
        return userInput.split(TIME_SEPARATOR)[1];
    }

    @Override
    public String toString() {
        return String.format("(E) %s (at: %s)", super.toString(), this.time);
    }
}
