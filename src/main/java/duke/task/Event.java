package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

/**
 * This class encapsulates a task with a start time and end time.
 */
public class Event extends Task {
    private static final String NO_TIMERANGE_MESSAGE = "An Event-type Task must be provided with a time range."
            + " Use the /at parameter to add a time range.";
    private static final String INVALID_DATE_MESSAGE = "Please provide me valid date(s) in the following format:\n"
            + "  YYYY1-MM1-DD1 YYYY2-MM2-DD2\n"
            + "i.e. 29th February 2000 to 2nd March 2000 is 2000-02-29 2000-03-02."
            + " You can provide only one date if you choose.";
    private static final String INVALID_DATERANGE_MESSAGE = "The second date is prior to the first date.";

    /** The starting date of this task. */
    protected LocalDate startTime;

    /** The ending date of this task. */
    protected LocalDate endTime;

    /**
     * Constructs a not completed Event with the specified description and time range parameter.
     *
     * @param desc the description of this Event.
     * @param timeRange the time range of this Event.
     * @throws DukeException if desc is null or empty, or if the time range was unable to be parsed into a date.
     */
    public Event(String desc, String timeRange) throws DukeException {
        super(desc);
        if (timeRange == null || timeRange.equals("")) {
            throw new DukeException(Event.NO_TIMERANGE_MESSAGE);
        }
        parseTimeRange(timeRange);
    }

    /**
     * Constructs an Event with the specified description, time range, and state of completion parameter.
     *
     * @param desc the description of this Event.
     * @param timeRange the time range of this Event.
     * @param isDone the state of completion of this Event.
     * @throws DukeException if desc is null or empty, or if the time range was unable to be parsed into a date.
     */
    public Event(String desc, String timeRange, boolean isDone) throws DukeException {
        super(desc, isDone);
        if (timeRange == null || timeRange.equals("")) {
            throw new DukeException(Event.NO_TIMERANGE_MESSAGE);
        }
        parseTimeRange(timeRange);
    }

    private void parseTimeRange(String timeRange) throws DukeException {
        String[] splitTimeRange = timeRange.split("\\s+");
        if (splitTimeRange.length > 2) {
            throw new DukeException(INVALID_DATE_MESSAGE);
        }
        try {
            this.startTime = LocalDate.parse(splitTimeRange[0]);
            if (splitTimeRange.length > 1) {
                this.endTime = LocalDate.parse(splitTimeRange[1]);
            } else {
                this.endTime = this.startTime;
            }
        } catch (DateTimeException e) {
            throw new DukeException(INVALID_DATE_MESSAGE);
        }
        if (this.endTime.compareTo(this.startTime) < 0) {
            throw new DukeException(INVALID_DATERANGE_MESSAGE);
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy");
        if (this.startTime.equals(this.endTime)) {
            return String.format("[E]%s (at: %s)", super.toString(), this.startTime.format(formatter));
        } else {
            return String.format("[E]%s (at: %s - %s)", super.toString(), this.startTime.format(formatter),
                    this.endTime.format((formatter)));
        }

    }

    @Override
    public String toStorageString() {
        return String.format("%s / E / %s %s", super.toStorageString(), this.startTime, this.endTime);
    }

}
