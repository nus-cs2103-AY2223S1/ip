package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * Abstract class that encapsulates the logic of a task.
 */
public abstract class Task {
    /* Constant fields representing the input and output format of the dates. */
    public static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    public static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
    protected static final String MISFORMAT_DATE = "Your date needs to be in dd-MM-yyyy HHmm format!";
    protected static final String MISFORMAT_PRIORITY = "Please specify a priority level using /p!";

    /* Task Information */
    protected String description;
    protected boolean isDone;
    protected Priority priority;

    /**
     * Constructor for a Task.
     *
     * @param description Description of the task.
     */
    public Task(String description, Priority priority) {
        this.description = description;
        this.priority = priority;
        this.isDone = false;
    }

    /**
     * Splits the priority field from the other details.
     *
     * @param in Input string to be split containing priority.
     * @return Array of 2 elements, split contents in 1st address, and priority in 2nd.
     * @throws DukeException if priority is not found/not included.
     */
    protected static String[] splitPriority(String in) throws DukeException {
        String[] prioritySplit = in.split(" */p* ");
        if (prioritySplit.length != 2) {
            throw new DukeException(MISFORMAT_PRIORITY);
        }
        return prioritySplit;
    }

    /**
     * Parses the priority input.
     *
     * @param priorityString Input string containing the priority.
     * @return Priority enum of HIGH/MEDIUM/LOW/NONE.
     * @throws DukeException If invalid priority is input.
     */
    protected static Priority parsePriority(String priorityString) throws DukeException {
        Priority priority;
        try {
            priority = Priority.getPriority(priorityString);
        } catch (IllegalArgumentException a) {
            throw new DukeException(Priority.UNKNOWN_PRIORITY);
        }
        return priority;
    }

    /**
     * Parses time for relevant subclasses.
     *
     * @param dateString Input string containing date.
     * @return Date of LocalDateTime type.
     * @throws DukeException If date is misformatted.
     */
    protected static LocalDateTime parseTime(String dateString) throws DukeException {
        LocalDateTime event;
        try {
            event = LocalDateTime.parse(dateString, INPUT_DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeException(MISFORMAT_DATE);
        }
        return event;
    }

    /**
     * Returns a string representation for the task's current status.
     *
     * @return String representation of the task's status.
     */
    protected String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Overrides toString method for a Task.
     *
     * @return String representation of the Task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    /**
     * Formats the task into a suitable format for files
     *
     * @return String representation of the Task in saved format.
     */
    public String saveFormat() {
        return String.format("%s | %d | %s", this.priority.saveFormat(), this.isDone ? 1 : 0, this.description);
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Gets the description of the task.
     *
     * @return String representation of the description of the task.
     */
    public String getDescription() {
        return this.description;
    }
}
