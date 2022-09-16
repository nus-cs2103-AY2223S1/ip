package hazell.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import hazell.exceptions.TaskDescriptionEmpty;

/**
 * A task that needs to be done by a specific time.
 */
public class Deadline extends TimeSensitiveTask {
    private static final String typeIcon = "D";
    private String time;

    protected Deadline(boolean isDone, String description, String time) {
        super(isDone, description);
        this.time = time;
    }

    /**
     * Creates a new Deadline task via factory method.
     * @param description Description of the deadline
     * @param time The time by when the deadline needs to be completed by
     * @return A Deadline
     * @throws TaskDescriptionEmpty If empty description is given
     */
    public static Deadline create(String description, String time) throws TaskDescriptionEmpty {
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
                this.time);
    }

    /**
     * Recreates a Deadline object from a string.
     *
     * @param words An array of words in which the original Deadline was serialised into
     * @return The unserialised Deadline object
     */
    public static Deadline unserialise(String[] words) {
        return new Deadline(
                words[1].equals("1"),
                words[2],
                words[3]);
    }

    @Override
    public void postpone(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        String[] words = this.time.split(" ");
        StringBuilder sbFormattedTime = new StringBuilder();
        if (words.length >= 1) {
            try {
                LocalDate date = LocalDate.parse(words[0]);
                sbFormattedTime.append(date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
            } catch (DateTimeParseException ex) {
                sbFormattedTime.append(this.time);
            }
        }
        assert sbFormattedTime.length() != 0 : "Timestring formatting error occured";
        return String.format("[%s]%s (by: %s)", typeIcon, super.toString(), sbFormattedTime);
    }
}
