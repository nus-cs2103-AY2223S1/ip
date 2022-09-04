package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private LocalDate date;
    public static final String TASK_TYPE = "E";
    public static final String CONNECTOR = "at";

    /**
     * Constructs an event task.
     *
     * @param taskDescription Description of event task.
     * @param date Date of event task.
     */
    public Event(String taskDescription, LocalDate date) {
        super(taskDescription);
        this.date = date;
    }

    /**
     * Constructs an event task.
     *
     * @param taskDescription Description of event task.
     * @param isDone Status of event task.
     * @param date Date of event task.
     */
    public Event(String taskDescription, boolean isDone, LocalDate date) {
        super(taskDescription, isDone);
        this.date = date;
    }

    /**
     * Returns event task.
     *
     * @return event task.
     */
    @Override
    public String toString() {
        return "[" + Event.TASK_TYPE + "]" + super.toString() + " (at: " + this.date.format(DateTimeFormatter
                .ofPattern(Task.LOAD_DATE_FORMAT)) + ")";
    }
}
