import java.time.temporal.TemporalAccessor;

public class Deadline extends Task {
    private static final String END_DATE_TIME_SEPARATOR = " /by ";

    private final TemporalAccessor endDateTime;

    private Deadline(String description, TemporalAccessor endDateTime) {
        this(description, false, endDateTime);
    }

    private Deadline(String description, boolean isDone, TemporalAccessor endDateTime) {
        super(description, isDone);
        this.endDateTime = endDateTime;
    }

    public static Deadline fromUserInput(String userInput) throws InvalidTaskFormatException {
        String[] splitUserInput = userInput.split(END_DATE_TIME_SEPARATOR, 2);
        if (splitUserInput.length < 2) {
            throw new InvalidTaskFormatException("No end time was provided for this deadline.");
        }
        TemporalAccessor endDateTime = DateTimeUtil.parseCompactDateTime(splitUserInput[1]);
        return new Deadline(splitUserInput[0], endDateTime);
    }

     public static Deadline fromEncodedString(String encodedString) throws InvalidTaskDataException {
         String[] splitTaskData = encodedString.split("\\|");
         if (splitTaskData.length < 4) {
             throw new InvalidTaskDataException("The data for this deadline is not formatted correctly.");
         }
         String description = splitTaskData[2];
         boolean isDone = splitTaskData[1].equals("1");
         TemporalAccessor endDateTime = DateTimeUtil.parseCompactDateTime(splitTaskData[3]);
         return new Deadline(description, isDone, endDateTime);
     }

    @Override
    public String toEncodedString() {
        return String.format("D|%s|%s", super.toEncodedString(),
                DateTimeUtil.formatCompactDateTime(this.endDateTime));
    }

    @Override
    public String toString() {
        return String.format("(D) %s (by: %s)", super.toString(),
                DateTimeUtil.formatPrettyDateTime(this.endDateTime));
    }
}
