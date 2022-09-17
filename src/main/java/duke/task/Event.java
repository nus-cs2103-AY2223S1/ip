package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.Nullable;

import duke.date.DateTimeParse;
import duke.exception.DukeException;

/**
 * An event is a task that starts at a specific time and ends at a specific time.
 */
public class Event extends Task {
    private static final String END_BEFORE_START_ERROR_MESSAGE = "Start datetime %s cannot be after end datetime %s";
    protected LocalDateTime eventStartDatetime;
    protected LocalDateTime eventEndDatetime;

    /**
     * Constructs an event task.
     *
     * @param description Description of the event task.
     * @param eventStartDatetime The start datetime of the event.
     * @param eventEndDatetime The end datetime of the event.
     * @throws DukeException If the start-end datetime is invalid.
     */
    public Event(String description, LocalDateTime eventStartDatetime,
                 LocalDateTime eventEndDatetime) throws DukeException {
        super(description);
        isValidStartEndDateTime(eventStartDatetime, eventEndDatetime);
        this.eventStartDatetime = eventStartDatetime;
        this.eventEndDatetime = eventEndDatetime;
    }

    /**
     * Constructs an event task with a specified completion status.
     *
     * @param description Description of the event task.
     * @param eventStartDatetime The start datetime of the event.
     * @param eventEndDatetime The end datetime of the event.
     * @param isCompleted Whether the event task is done or not.
     * @param completionDateTime The datetime when the task was marked completed.
     * @throws DukeException If the start-end datetime is invalid.
     */
    public Event(String description, LocalDateTime eventStartDatetime,
                 LocalDateTime eventEndDatetime, boolean isCompleted,
                 @Nullable LocalDateTime completionDateTime) throws DukeException {
        super(description, isCompleted, completionDateTime);
        isValidStartEndDateTime(eventStartDatetime, eventEndDatetime);
        this.eventStartDatetime = eventStartDatetime;
        this.eventEndDatetime = eventEndDatetime;
    }

    /**
     * Constructs an event from a given string in the format:
     * {description} /at {start} /to {end}.
     *
     * @param cmd The string to construct the event from.
     * @return The constructed event based on the specifications of the command.
     * @throws DukeException If the command is invalid.
     */
    public static Event construct(String cmd) throws DukeException {
        if (!cmd.matches("(?i)^.+\\s/(at)\\s.+\\s/(to)\\s.+")) {
            String errorMessage = "hmm are you trying to edit an event?"
                    + " make sure the command is in the format: edit {description} /at"
                    + " {start} /to {end}";
            throw new DukeException(errorMessage);
        }

        String[] sp = cmd.split("\\s/((at)|(to))\\s", 3);
        String description = sp[0];
        String startDate = sp[1];
        String endDate = sp[2];
        LocalDateTime startDatetime = DateTimeParse.parseDateTime(startDate);
        LocalDateTime endDatetime = DateTimeParse.parseDateTime(endDate);
        isValidStartEndDateTime(startDatetime, endDatetime);

        return new Event(description, startDatetime, endDatetime);
    }

    /**
     * Returns a String representation of the start datetime object associated to the
     * event task in EEEE, dd MMM yyyy HH:mm format.
     *
     * @return String representation of the start datetime in EEEE, dd MMM yyyy HH:mm format.
     */
    public String getStartDatetimeString() {
        DateTimeFormatter dayDateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy HH:mm");
        return eventStartDatetime.format(dayDateTimeFormatter);
    }

    /**
     * Returns a String representation of the end datetime object associated to the
     * event task in EEEE, dd MMM yyyy HH:mm format.
     *
     * @return String representation of the end datetime in EEEE, dd MMM yyyy HH:mm format.
     */
    public String getEndDatetimeString() {
        DateTimeFormatter dayDateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy HH:mm");
        return eventEndDatetime.format(dayDateTimeFormatter);
    }

    /**
     * Validates whether the start and end date time are valid event start-end date times.
     *
     * @param startDatetime The start date time of the event.
     * @param endDatetime The end date time of the event.
     * @return True if the start-end date time is valid (start is not after end date time).
     * @throws DukeException If the start-end date time is invalid (start after end date time).
     */
    private static boolean isValidStartEndDateTime(LocalDateTime startDatetime,
                                          LocalDateTime endDatetime) throws DukeException {
        // ensures that the start and end datetime is valid (start cannot be after end)
        if (startDatetime.isAfter(endDatetime)) {
            String errorMessage = String.format(END_BEFORE_START_ERROR_MESSAGE,
                    startDatetime, endDatetime);
            throw new DukeException(errorMessage);
        } else {
            return true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event edit(String userEditInput) throws DukeException {
        // this kind of stupidly edits the event by constructing a dummy event in
        // order to reuse the validation method, to look into whether there might
        // be better ways of going about this
        Event editedEvent = construct(userEditInput);
        description = editedEvent.description;
        eventStartDatetime = editedEvent.eventStartDatetime;
        eventEndDatetime = editedEvent.eventEndDatetime;
        return this;
    }

    /**
     * Checks whether the event task is active during the specified date.
     * Will return true only if the task is overdue- meaning the event is over
     * (relative to the specified date), but it has not been marked done,
     * or if the specified date falls between the event start and end time.
     *
     * @param date The date to check whether the task is active.
     * @return Whether the event task is active (overdue or happens on the specified date).
     */
    @Override
    public boolean isActive(LocalDate date) {
        assert !eventStartDatetime.isAfter(eventEndDatetime)
                : "Event start time should not be after event end time!";
        // since event end date should not be before the start date, to check
        // if the event has passed, it suffices to just check the end date
        boolean hasPassed = eventEndDatetime.toLocalDate().isBefore(date);
        boolean isDue = hasPassed && !isDone;
        // an event is happening if it is between the start and end dates (inclusive),
        // this check ignores time
        boolean isHappening = !eventStartDatetime.toLocalDate().isAfter(date)
                && !eventEndDatetime.toLocalDate().isBefore(date);
        return isDue || isHappening;
    }

    /**
     * Parses the Event into a savable string format, ready to be written to the hard disk.
     *
     * @return Savable string representation of the Deadline Task.
     */
    @Override
    public String toSaveFormat() {
        String savableCompletion = isDone ? "Y" : "N";
        // escape instances of deliminator in task description
        String escapedDescription = description.replace("|", "\\|");
        return String.format("E | %s | %s | %s | %s | %s", savableCompletion, escapedDescription,
                eventStartDatetime, eventEndDatetime, completionDateTime);
    }

    /**
     * Returns a string representation for the event task, prefixed with a [E],
     * followed by the event status, and the event description and start to end time formatted
     * in EEEE, dd MMM yyyy HH:mm format.
     *
     * @return The string representation of the event task.
     */
    @Override
    public String toString() {
        String completionDescription = super.toString();
        String formattedStartDateTime = getStartDatetimeString();
        String formattedEndDateTime = getEndDatetimeString();
        return String.format("[E]%s (at: %s to %s)", completionDescription,
                formattedStartDateTime, formattedEndDateTime);
    }
}
