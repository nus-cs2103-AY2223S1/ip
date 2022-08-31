package duke.task;

import duke.Task;

/**
 * Represents an Event task
 */
public class Event extends Task {
    private String dateTime;

    /**
     * Initialzs event instance
     * @param item string entered by user
     * @param dateTime date entered by user
     */
    public Event(String item, String dateTime) {
        this.setItem(item);
        this.dateTime = dateTime;
    }

    /**
     * Gets the string representation of the task
     * @return string representation of the task
     */
    public String getTask() {
        return "[E] " + this.getStatusIcon() + " " + this.getItem() + " (at: " + this.dateTime + ")";
    }

    /**
     * Gets the representation of the task that is stored in the data file
     * @return string representation of the task that is stored in the data file
     */
    public String getFileLine() {
        return "[E]" + "##" + this.getStatusIcon() + "##" + this.getItem() + "##" + this.dateTime;
    }
}
