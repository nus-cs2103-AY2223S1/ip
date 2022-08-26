package BetaGo.Tasks;

/**
 * Event class is a type of task where users can store their task description
 * and additional location information.
 */
public class Event extends Task {

    private String at;

    /**
     * Constructor for Event task.
     *
     * @param description Description of the task.
     * @param at Location of the event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns string representation for event task with location.
     *
     * @return String representation for event task.
     */
    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.getTaskDescription() + " (at: " + at + ")";
    }

    /**
     * Returns corresponding string representation of the event task that is saved in the data file.
     *
     * @return String representation for event task to be saved in data file.
     */
    @Override
    public String saveTask() {
        String icon;
        if (this.getStatusIcon() == "X") {
            icon = "1";
        } else {
            icon = "0";
        }
        return "E , " + icon + " , " + this.description + " , " + this.at + "\n";
    }
}
