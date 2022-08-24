package scottie.tasks;

import scottie.common.DateTimeUtil;

import java.time.temporal.TemporalAccessor;

public class Deadline extends Task {
    private final TemporalAccessor endDateTime;

    public Deadline(String description, TemporalAccessor endDateTime) {
        this(description, false, endDateTime);
    }

    private Deadline(String description, boolean isDone, TemporalAccessor endDateTime) {
        super(description, isDone);
        this.endDateTime = endDateTime;
    }

     static Deadline fromEncodedString(String encodedString) throws InvalidTaskDataException {
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
    String toEncodedString() {
        return String.format("D|%s|%s", super.toEncodedString(),
                DateTimeUtil.formatCompactDateTime(this.endDateTime));
    }

    @Override
    public String toString() {
        return String.format("(D) %s (by: %s)", super.toString(),
                DateTimeUtil.formatPrettyDateTime(this.endDateTime));
    }
}
