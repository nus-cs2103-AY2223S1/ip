package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * Event task with TaskType, description,
 * boolean to check whether the task is done, and time of deadline.
 */
public class Event extends Task {

    private LocalDateTime atTime;

    /**
     * Constructor for Event.
     *
     * @param taskType TaskType of event
     * @param name Description of the event
     * @param isMarked Denotes whether the event is done yet
     * @param timeStr Time of event
     */
    public Event(TaskType taskType, String name, boolean isMarked, String timeStr) throws DukeException {
        super(taskType, name, isMarked);
        try {
            String format = "HHmm, d/MM/yyyy";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            this.atTime = LocalDateTime.parse(timeStr, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Enter your atTime in this format : HHmm, d/MM/yyyy");
        }
    }

    /**
     * @return Time of event
     */
    public LocalDateTime getAtTime() {
        return this.atTime;
    }

    /**
     * @param newAtTime new at time stated
     * @throws DukeException If invalid time format
     */
    public void setAtTime(String newAtTime) throws DukeException {
        try {
            String format = "HHmm, d/MM/yyyy";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            this.atTime = LocalDateTime.parse(newAtTime, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Enter your atTime in this format : HHmm, d/MM/yyyy");
        }
    }

    /**
     * {@inheritDoc}
     * @return String of event with details, TaskType, name, isMarked and time.
     */
    @Override
    public String toString() {
        String format = "hh:mm a, EEE, d MMM yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return "[E]" + super.toString()
                + " (at: " + atTime.format(formatter) + ")";
    }
}