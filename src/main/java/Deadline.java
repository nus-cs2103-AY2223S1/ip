public class Deadline extends Task {
    private static final String END_TIME_SEPARATOR = " /by ";

    private final String endTime;

    private Deadline(String description, String endTime) {
        this(description, false, endTime);
    }

    private Deadline(String description, boolean isDone, String endTime) {
        super(description, isDone);
        this.endTime = endTime;
    }

    public static Deadline fromUserInput(String userInput) throws InvalidTaskFormatException {
        String[] splitUserInput = userInput.split(END_TIME_SEPARATOR, 2);
        if (splitUserInput.length < 2) {
            throw new InvalidTaskFormatException("No end time was provided for this deadline.");
        }
        return new Deadline(splitUserInput[0], splitUserInput[1]);
    }

     public static Deadline fromEncodedString(String encodedString) throws InvalidTaskDataException {
         String[] splitTaskData = encodedString.split("\\|");
         if (splitTaskData.length < 4) {
             throw new InvalidTaskDataException("The data for this deadline is not formatted correctly.");
         }
         String description = splitTaskData[2];
         boolean isDone = splitTaskData[1].equals("1");
         String endTime = splitTaskData[3];
         return new Deadline(description, isDone, endTime);
     }

    @Override
    public String toEncodedString() {
        return String.format("D|%s|%s", super.toEncodedString(), this.endTime);
    }

    @Override
    public String toString() {
        return String.format("(D) %s (by: %s)", super.toString(), this.endTime);
    }
}
