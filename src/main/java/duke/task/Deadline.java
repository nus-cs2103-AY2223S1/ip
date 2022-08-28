package duke.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Class that represents a deadline.
 */
public class Deadline extends Task {
    /** Due date of the deadline */
    private LocalDate byDate;
    /** Due date-time of the deadline */
    private LocalDateTime byDateTime;
    /** If deadline has a specified time */
    private final boolean hasTime;

    /**
     * Constructor to initialize the description, completion status and due date of
     * the deadline. Completion status is always false when deadline is first created.
     * 
     * @param desc The deadline description.
     * @param byDate The deadline due date.
     */
    public Deadline(String desc, LocalDate byDate) {
        super(desc);
        this.byDate = byDate;
        this.hasTime = false;
    }

    /**
     * Constructor to initialize the description, completion status and due date-time of
     * the deadline. Completion status is always false when deadline is first created.
     * 
     * @param desc The deadline description.
     * @param byDateTime The deadline due date-time.
     */
    public Deadline(String desc, LocalDateTime byDateTime) {
        super(desc);
        this.byDateTime = byDateTime;
        this.hasTime = true;
    }

    /**
     * Constructor to initialize the description, completion status and due date of
     * the deadline.
     *
     * @param desc The deadline description.
     * @param byDate The deadline due date.
     * @param isDone The deadline completion status.
     */
    public Deadline(String desc, LocalDate byDate, boolean isDone) {
        super(desc, isDone);
        this.byDate = byDate;
        this.hasTime = false;
    }

    /**
     * Constructor to initialize the description, completion status and due date-time of
     * the deadline.
     *
     * @param desc The deadline description.
     * @param byDateTime The deadline due date-time.
     * @param isDone The deadline completion status.
     */
    public Deadline(String desc, LocalDateTime byDateTime, boolean isDone) {
        super(desc, isDone);
        this.byDateTime = byDateTime;
        this.hasTime = true;
    }
    
    private String formatBy() {
        if (this.hasTime) {
            return this.byDateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a"));
        } else {
            return this.byDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        }
    }

    private String saveFormatBy() {
        if (this.hasTime) {
            return this.byDateTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } else {
            return this.byDate.format(DateTimeFormatter.ofPattern("d/M/yyyy"));
        }
    }

    /**
     * Converts the deadline to its saved format.
     * 
     * @return The string representation of the saved format of the deadline.
     */
    @Override
    public String getSaveFormat() {
        return "D " + super.getSaveFormat() + " | " + this.saveFormatBy();
    }

    /**
     * Returns the string representation of the deadline.
     * 
     * @return The string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.formatBy() + ")";
    }
}
