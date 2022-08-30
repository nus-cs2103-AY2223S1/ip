package scottie.tasks;

import java.time.temporal.TemporalAccessor;

import scottie.common.DateTimeUtil;

/**
 * Encapsulates a task which needs to be done by a certain date and time.
 */
public class Deadline extends Task {
    private final TemporalAccessor endDateTime;

    /**
     * Constructs a Deadline with the given description and endDateTime.
     * The Deadline defaults to being not done.
     *
     * @param description The description of this Deadline.
     * @param endDateTime The end date and time of this Deadline.
     */
    public Deadline(String description, TemporalAccessor endDateTime) {
        this(description, false, endDateTime);
    }

    /**
     * Constructs a Deadline with the given description, isDone status and endDateTime.
     *
     * @param description The description of this Deadline.
     * @param isDone Whether this Deadline is done.
     * @param endDateTime The date and time of this Deadline.
     */
    private Deadline(String description, boolean isDone, TemporalAccessor endDateTime) {
        super(description, isDone);
        this.endDateTime = endDateTime;
    }

    /**
     * Returns a Deadline constructed based on the data in the provided string.
     *
     * @param encodedString The string containing the data for the Deadline.
     * @return The constructed Deadline.
     * @throws InvalidTaskDataException If the string is not formatted correctly.
     */
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

    /**
     * {@inheritDoc}
     */
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
