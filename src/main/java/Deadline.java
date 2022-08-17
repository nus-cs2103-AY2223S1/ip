public class Deadline extends Task {
    private static final String END_TIME_SEPARATOR = " /by ";

    private final String endTime;

    public Deadline(String userInput) {
        super(extractDescription(userInput));
        this.endTime = extractEndTime(userInput);
    }

    private static String extractDescription(String userInput) {
        return userInput.split(END_TIME_SEPARATOR)[0];
    }

    private static String extractEndTime(String userInput) {
        return userInput.split(END_TIME_SEPARATOR)[1];
    }

    @Override
    public String toString() {
        return String.format("(D) %s (by: %s)", super.toString(), this.endTime);
    }
}
