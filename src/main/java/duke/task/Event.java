package duke.task;
public class Event extends Task {
    private String date;

    /**
     * Constructor for Event.
     *
     * @param command The String array containing description and date of event.
     */
    public Event (String[] command) {

        super(command[0]);
        this.date = command[1];
    }

    /**
     * Converts task to String in format for output file.
     *
     * @return The task description for output text file.
     */
    @Override
    public String tofileString() {
        return "E|" + super.tofileString() + "|" + this.date;
    }

    /**
     * Returns String representation of task.
     * Format is for output during program runtime.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " at: " + date;
    }
}
