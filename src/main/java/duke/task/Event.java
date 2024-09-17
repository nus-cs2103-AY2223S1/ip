package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Represents a task that starts and ends at a specific time.
 */
public class Event extends Task {
    private String preposition;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private LocalTime endTime;

    /**
     * Constructs a task that starts at the specified date and time and ends
     * on the specified date and time.
     *
     * @param description A brief description of the task.
     * @param preposition Relevant preposition.
     * @param startDateTime The date and time the task occurs on.
     * @param endDateTime The date and time the task ends on.
     */
    public Event(String description, String preposition, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description);
        this.preposition = preposition;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Constructs a task that starts and ends on the same date.
     *
     * @param description A brief description of the task.
     * @param preposition Relevant preposition.
     * @param startDateTime The date and time the task occurs on.
     * @param endTime The time the task ends at.
     */
    public Event(String description, String preposition, LocalDateTime startDateTime, LocalTime endTime) {
        super(description);
        this.preposition = preposition;
        this.startDateTime = startDateTime;
        this.endTime = endTime;
    }

    @Override
    public boolean isOn(LocalDate searchDate) {
        return startDateTime.toLocalDate().equals(searchDate);
    }

    @Override
    public String fileDescription() {
        if (endTime == null) {
            return "E | " + super.fileDescription() + " | " + preposition
                    + " | " + startDateTime + " | " + endDateTime;
        } else {
            return "E | " + super.fileDescription() + " | " + preposition
                    + " | " + startDateTime + " | " + endTime;
        }
    }

    @Override
    public String toString() {
        if (endTime == null) {
            return "[E]" + super.toString() + " (" + preposition + ": "
                    + startDateTime.format(super.dateTimeFormatter) + " to "
                    + endDateTime.format(super.dateTimeFormatter) + ")";
        } else {
            return "[E]" + super.toString() + " (" + preposition + ": "
                    + startDateTime.format(super.dateTimeFormatter) + " to "
                    + endTime.format(super.timeFormatter) + ")";
        }
    }
}
