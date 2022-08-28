package duke;

/**
 * Event class to represent a new Event task.
 *
 * @author Sheryl Kong (A0240686Y)
 */

public class Event extends Task {
    private String date;

    /**
     * Constructor for Event class
     *
     * @param description description of the Event task
     * @param isDone indicates if the task is marked as done
     * @param date due date of the Event task
     */

    public Event(String description, boolean isDone, String date) {
        super(description.trim());
        this.isDone = isDone;
        this.date = date.trim();
        Task.addTaskCount();
    }

    @Override
    public String toString() {
        return String.format("E | %s | %s | %s", this.getStatusIcon(), this.description, this.date);
    }

}
