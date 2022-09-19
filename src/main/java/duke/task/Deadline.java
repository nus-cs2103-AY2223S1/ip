package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DeadlineException;

/**
 * Deadline task for Duke application
 *
 * @author Farrel Dwireswara Salim
 */
public class Deadline extends Task {
    private final String deadlineString;
    private final LocalDate deadline;

    /**
     * Constructs a new Deadline instance.
     *
     * @param description the description of the task.
     * @param deadlineString the string which represents the deadline of the task.
     * @param tags the tags of the task.
     * @throws DeadlineException If deadlineString is not valid.
     */
    public Deadline(String description, String deadlineString, String ... tags)
            throws DeadlineException {
        super(description, tags);

        try {
            this.deadlineString = deadlineString;
            this.deadline = LocalDate.parse(deadlineString);
        } catch (DateTimeParseException error) {
            throw new DeadlineException("The deadline given is not a valid date. "
                    + "Try to represent the deadline in yyyy-mm-dd format.");
        }
    }

    /**
     * Gets the string representation of the Deadline.
     *
     * @return the string which represents current Deadline.
     */
    @Override
    public String toString() {
        String tagInfo = super.getTagsString().isEmpty()
                ? ""
                : "[" + super.getTagsString() + "]";

        return String.format("[D]%s (by: %s) %s", super.toString(),
                this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                tagInfo);
    }

    /**
     * Transforms the Deadline to a string that is compatible to Duke's storage.
     *
     * @return the string to be saved in storage.
     */
    @Override
    public String toStorageRepresentation() {
        return String.format("D|%s|%s#%s", super.toStorageRepresentation(),
                this.deadlineString, super.getTagsString());
    }

    /**
     * Returns true if the Deadline is on the given date.
     *
     * @param selectedDates the date objects.
     * @return true if the Deadline is on the selected date, false otherwise.
     */
    @Override
    protected boolean isOnGivenDate(LocalDate ... selectedDates) {
        for (int i = 0; i < selectedDates.length; i++) {
            if (this.deadline.equals(selectedDates[i])) {
                return true;
            }
        }

        return false;
    }
}
