package gina.task;

import java.util.Arrays;

/**
 * Represents a to-do.
 */
public class ToDo extends Task {
    private static final int DESCRIPTION_START_INDEX = 10;
    /**
     * Constructs a to-do with the specified description.
     *
     * @param description The specified description.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Creates a to-do using the storage data.
     *
     * @param line The line representing the to-do in the storage.
     * @return The to-do with the specified description.
     */
    public static ToDo createToDoFromString(String line) {
        return new ToDo(line.substring(DESCRIPTION_START_INDEX));
    }

    /**
     * {@inheritDoc}
     */
    public boolean isOnThisDate(String dateStr) {
        return false;
    }

    public boolean doesDescriptionContain(String input) {
        return Arrays.asList(description.split(" ")).contains(input);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
