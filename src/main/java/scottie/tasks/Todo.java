package scottie.tasks;

import java.time.LocalDateTime;

/**
 * Encapsulates a regular task with no extra information.
 */
public class Todo extends Task {
    private static final String INVALID_DATA_FORMAT_MESSAGE = "The data for this to-do is not formatted correctly.";

    /**
     * Constructs a Todo with the given description.
     * The Todo defaults to being not done.
     *
     * @param description The description of this Todo.
     */
    public Todo(String description) {
        this(description, false);
    }

    /**
     * Constructs a Todo with the given description and isDone status.
     *
     * @param description The description of this Todo.
     * @param isDone Whether this Todo is done.
     */
    private Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a Todo constructed based on the data in the provided string.
     *
     * @param encodedString The string containing the data for the Todo.
     * @return The constructed Todo.
     * @throws InvalidTaskDataException If the string is not formatted correctly.
     */
    static Todo fromEncodedString(String encodedString) throws InvalidTaskDataException {
        String[] splitTaskData = encodedString.split("\\|");
        if (splitTaskData.length < 3) {
            throw new InvalidTaskDataException(INVALID_DATA_FORMAT_MESSAGE);
        }
        String description = splitTaskData[2];
        boolean isDone = splitTaskData[1].equals("1");
        return new Todo(description, isDone);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    String toEncodedString() {
        return String.format("T|%s", super.toEncodedString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    LocalDateTime getDateTime() {
        return null;
    }

    @Override
    public String toString() {
        return "(T) " + super.toString();
    }
}
