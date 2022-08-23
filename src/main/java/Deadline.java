public class Deadline extends Task {
    private static final String END_TIME_SEPARATOR = " /by ";

    private final String endTime;

    private Deadline(String description, String endTime) {
        super(description);
        this.endTime = endTime;
    }

    public static Deadline fromUserInput(String userInput) throws InvalidTaskFormatException {
        String[] splitUserInput = userInput.split(END_TIME_SEPARATOR, 2);
        if (splitUserInput.length < 2) {
            throw new InvalidTaskFormatException("No end time was provided for this deadline.");
        }
        return new Deadline(splitUserInput[0], splitUserInput[1]);
    }

    @Override
    public String toString() {
        return String.format("(D) %s (by: %s)", super.toString(), this.endTime);
    }
}
