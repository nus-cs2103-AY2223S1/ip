/**
 * The Event class extends from Task as it is a more specific/well-defined
 * task, it is differentiated with the "event" keyword in user-input.
 */
public class Event extends Task {

    //String field to store the details after /at keyword
    private String at;

    /**
     * Constructor method for an instance of Event and it also utilises
     * the constructor of it's parent class (Task)
     *
     * @param description String that contains the details about the task
     * @param at String that contains the information of the specific time of event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * A string method to represent the Event task.
     *
     * @return a string representation of a task with a specific time-frame.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
