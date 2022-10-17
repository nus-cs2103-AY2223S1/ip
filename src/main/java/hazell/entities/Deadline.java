package hazell.entities;

import hazell.exceptions.HazellException;
import hazell.exceptions.TaskDescriptionEmpty;

/**
 * A task that needs to be done by a specific time.
 */
public class Deadline extends TimeSensitiveTask {
    private static final String typeIcon = "D";

    protected Deadline(boolean isDone, String description, String time) throws HazellException {
        super(isDone, description, toLocalDate(time));
    }

    /**
     * Creates a new Deadline task via factory method.
     * @param description Description of the deadline
     * @param time The time by when the deadline needs to be completed by
     * @return A Deadline
     * @throws TaskDescriptionEmpty If empty description is given
     */
    public static Deadline create(String description, String time) throws HazellException {
        Deadline deadline = new Deadline(false, description, time);
        deadline.validate();
        return deadline;
    }

    @Override
    public String serialise() {
        return String.format("%s | %s | %s | %s",
                typeIcon,
                this.getIsDone() ? 1 : 0,
                this.getDescription(),
                this.getParsedTime());
    }

    /**
     * Recreates a Deadline object from a string.
     *
     * @param words An array of words in which the original Deadline was serialised into
     * @return The unserialised Deadline object
     */
    public static Deadline unserialise(String[] words) {
        try {
            return new Deadline(
                words[1].equals("1"),
                words[2],
                words[3]);
        } catch (HazellException ex) {
            // This will not occur assuming the text file is not tampered with.
            return null;
        }
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", typeIcon, super.toString(), getFriendlyTime());
    }
}
