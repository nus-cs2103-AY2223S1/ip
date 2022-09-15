package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class encapsulates an event item
 */
public class Event extends Task {
    private static final String TASK_TYPE = "E";
    protected LocalDate at;

    /**
     * Constructs a new Event
     * @param description The description of the event
     * @param at When the event is taking place
     */
    public Event(String description, String at) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(at, formatter);
        this.at = localDate;
    }

    /**
     * Creates a new event using the information provided
     * @param event The event information
     * @return An event
     */
    public static Event createEvent(String event) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String[] components = event.split(",");
        LocalDate eventDate = LocalDate.parse(components[3], format);
        Event e = new Event(components[2], eventDate.format(formatter));
        e.setIsDone(components[1].equals("true"));
        e.setDateMarked(components[4]);

        return e;
    }

    /**
     * Returns a string representation of the event object
     * @return A string representation of the event object
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String date = at.format(formatter);
        return "[E]" + super.toString() + String.format(" (at: %s)", date);
    }

    /**
     * Gets the date of the task
     * @return The date of the task
     */
    @Override
    public LocalDate getDate() {
        return at;
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
        String formattedDate = at.format(formatter);
        String sep = System.getProperty("line.separator");
        String dateMarkedCompleted = dateMarked == null
                ? "na"
                : dateMarked.format(formatter);
        String storageFormat = String.format("%s,%s,%s,%s,%s,%s", TASK_TYPE, isDone,
                description, formattedDate, dateMarkedCompleted, sep);
        return storageFormat;
    }
}
