package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

/**
 * This class encapsulates a task with a deadline.
 */
public class Deadline extends Task {
    private static final String NO_ENDTIME_MESSAGE = "A Deadline-type Task must be provided with an ending time."
            + " Use the /by parameter to add a deadline.";
    private static final String INVALID_DATE_MESSAGE = "Please provide me a valid date in the following format:\n"
            + "YYYY-MM-DD\ni.e. 29th February 2000 is 2000-02-29";

    /** The deadline of this task. */
    protected LocalDate endDate;

    /**
     * Constructs a not completed Deadline with the specified description and deadline parameter.
     *
     * @param desc the description of this Deadline.
     * @param endTime the deadline of this Deadline.
     * @throws DukeException if desc is null or empty, or if the deadline was unable to be parsed into a date.
     */
    public Deadline(String desc, String endTime) throws DukeException {
        super(desc);
        if (endTime == null || endTime.equals("")) {
            throw new DukeException(NO_ENDTIME_MESSAGE);
        }
        try {
            this.endDate = LocalDate.parse(endTime);
        } catch (DateTimeException e) {
            throw new DukeException(INVALID_DATE_MESSAGE, e);
        }

    }

    /**
     * Constructs a Deadline with the specified description, deadline, and state of completion parameter.
     *
     * @param desc the description of this Deadline.
     * @param endTime the deadline of this Deadline.
     * @param isDone the state of completion of this Deadline.
     * @throws DukeException if desc is null or empty, or if the deadline was unable to be parsed into a date.
     */
    public Deadline(String desc, String endTime, boolean isDone) throws DukeException {
        super(desc, isDone);
        if (endTime == null || endTime.equals("")) {
            throw new DukeException(NO_ENDTIME_MESSAGE);
        }
        try {
            this.endDate = LocalDate.parse(endTime);
        } catch (DateTimeException e) {
            throw new DukeException(INVALID_DATE_MESSAGE, e);
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy");
        return String.format("[D]%s (by: %s)", super.toString(), this.endDate.format(formatter));
    }

    @Override
    public String toStorageString() {
        return String.format("%s / D / %s", super.toStorageString(), this.endDate);
    }

}
