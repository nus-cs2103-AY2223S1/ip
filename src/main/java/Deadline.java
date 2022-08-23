import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class Deadline extends Task {
    private static final String END_DATE_TIME_SEPARATOR = " /by ";
    private static final DateTimeFormatter INPUT_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("d/M/yy[ HHmm]");
    private static final DateTimeFormatter OUTPUT_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("MMM d yyyy[ h:mma]");

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
        TemporalAccessor endDateTime = INPUT_DATE_TIME_FORMATTER.parseBest(splitUserInput[1],
                LocalDateTime::from, LocalDate::from);
        return new Deadline(splitUserInput[0], endDateTime);
    }

     public static Deadline fromEncodedString(String encodedString) throws InvalidTaskDataException {
         String[] splitTaskData = encodedString.split("\\|");
         if (splitTaskData.length < 4) {
             throw new InvalidTaskDataException("The data for this deadline is not formatted correctly.");
         }
         String description = splitTaskData[2];
         boolean isDone = splitTaskData[1].equals("1");
         TemporalAccessor endDateTime = INPUT_DATE_TIME_FORMATTER.parseBest(splitTaskData[3],
                 LocalDateTime::from, LocalDate::from);
         return new Deadline(description, isDone, endDateTime);
     }

    @Override
    public String toEncodedString() {
        return String.format("D|%s|%s", super.toEncodedString(),
                INPUT_DATE_TIME_FORMATTER.format(this.endDateTime));
    }

    @Override
    public String toString() {
        return String.format("(D) %s (by: %s)", super.toString(),
                OUTPUT_DATE_TIME_FORMATTER.format(this.endDateTime));
    }
}
