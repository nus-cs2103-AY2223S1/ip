package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class encapsulates a deadline item
 */
public class Deadline extends Task {
    private static final String TASK_TYPE = "D";
    protected LocalDate by;

    /**
     * Constructs a new Deadline
     * @param description The description of the deadline
     * @param by The date to complete the task by
     */
    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.by = LocalDate.parse(by, formatter);
    }

    /**
     * Creates a new deadline using the information provided
     * @param deadline Deadline information
     * @return A deadline task
     */
    public static Deadline createDeadline(String deadline) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String[] components = deadline.split(",");
        LocalDate eventDate = LocalDate.parse(components[3], format);
        Deadline d = new Deadline(components[2], eventDate.format(formatter));
        d.setIsDone(components[1].equals("true"));
        d.setDateMarked(components[4]);

        return d;
    }

    /**
     * Returns a string representation of the deadline object
     * @return A string representation of the deadline object
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String date = by.format(formatter);
        return "[D]" + super.toString() + String.format(" (by: %s)", date);
    }

    /**
     * Gets the date of the task
     * @return The date of the task
     */
    @Override
    public LocalDate getDate() {
        return by;
    }

    /**
     * Gets the task type
     * @return The task type
     */
    @Override
    public String getTaskType() {
        return TASK_TYPE;
    }

    /**
     * Converts the task to the storage format
     * @return The storage format
     */
    @Override
    public String stringify() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String formattedDate = by.format(formatter);
        String dateMarkedCompleted = dateMarked == null
                ? "na"
                : dateMarked.format(formatter);
        String sep = System.getProperty("line.separator");
        String storageFormat = String.format("%s,%s,%s,%s,%s,%s", TASK_TYPE, isDone,
                description, formattedDate, dateMarkedCompleted, sep);
        return storageFormat;
    }
}
