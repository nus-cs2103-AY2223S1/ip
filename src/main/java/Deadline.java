public class Deadline extends Task {
    private static final String END_TIME_SEPARATOR = " /by ";

    private final String endTime;

    public Deadline(String userInput) throws InvalidTaskFormatException {
        super(extractDescription(userInput));
        this.endTime = extractEndTime(userInput);
    }

    private static String extractDescription(String userInput) {
        return userInput.split(END_TIME_SEPARATOR, 2)[0];
    }

    private static String extractEndTime(String userInput) throws InvalidTaskFormatException {
        String[] splitUserInput = userInput.split(END_TIME_SEPARATOR, 2);
        if (splitUserInput.length < 2) {
            throw new InvalidTaskFormatException("No end time was provided for this deadline.");
        }
        return splitUserInput[1];
    }

    @Override
    public String toString() {
        return String.format("(D) %s (by: %s)", super.toString(), this.endTime);
    }
}
