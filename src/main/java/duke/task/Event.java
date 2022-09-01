package duke.task;

/**
 * Extends from Task as it is a more specific/well-defined
 * task, it is differentiated with the "event" keyword in user-input.
 */
public class Event extends Task {

    //String field to store the details after /at keyword
    private String at;

    /**
     * Constructor method for an instance of Event and it also utilises
     * the constructor of the parent class.
     *
     * @param description String that contains the details about the task
     * @param at String that contains the information of the specific time of event
     * @param isDone Boolean to keep track if the task has been marked before
     */
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Provides a String representation of the Event task.
     *
     * @return String representation of a task with a specific time-frame.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
