package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event that occurs at a specific timeline.
 */
public class Event extends Task {

    private String name;
    private String type;
    private String status;
    private LocalDateTime time;

    /**
     * Constructs an event.
     *
     * @param name event name.
     * @param time event time.
     */
    public Event(String name, LocalDateTime time) {
        this.name = name;
        this.status = "[ ]";
        this.type = "[E]";
        this.time = time;
    }

    /**
     * Sets the status of the event as specified.
     *
     * @param status the status of the event. [X] for done, [ ] for undone.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Prints the description of the event that is being added to the task list.
     */
    public void print() {
        System.out.println(Ui.ADD_TASK_HEADER + this.toString() + " Now you have "
                + Duke.count + " tasks in the list." + "\n" + Duke.LINE + "\n");
    }

    /**
     * Prints the description of the event.
     */
    public void list() {
        System.out.println(this.type + this.status + " " + this.name
                + "(at: " + formatDateString(this.time) + ")");
    }

    /**
     * Gets the name of the event.
     *
     * @return the string representation of the event name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the status of the event.
     *
     * @return the string representation of the event status.
     */
    @Override
    public String getStatus() {
        return this.status;
    }

    /**
     * Gets the type of the event.
     *
     * @return the string representation of the event type.
     */
    @Override
    public String getType() {
        return this.type;
    }

    /**
     * Gets the event time.
     *
     * @return the event time.
     */
    public LocalDateTime getTime() {
        return this.time;
    }

    /**
     * Returns the full string representation of the event.
     *
     * @return the complete string representation of the event.
     */
    public String toString() {
        return this.getType() + this.getStatus() + this.getName()
                + " (at: " + formatDateString(this.time) + ")" + "\n";
    }

    private String formatDateString (LocalDateTime date){
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
    }

    /**
     * Returns the string representation of the event.
     *
     * @return the string representation of the event.
     */
    @Override
    public String description() {
        return this.getName() + " (at: " + formatDateString(this.time) + ")";
    }
}
