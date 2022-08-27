package duke.task;

public class Event extends Task {
    private final String event;

    /**
     * Constructor for the Event class.
     *
     * @param taskName the name of the event task
     * @param event the event of the task
     */
    public Event(String taskName, String event) {
        super(taskName);
        this.event = event;
    }

    /**
     * Returns a reformatted string of the task to be stored in the text file.
     *
     * @return Reformatted string representation of the task
     */
    @Override
    public String formatTask() {
        return String.format("E | %s | %s | %s\n",
                (this.isDone ? "1" : "0"),
                this.taskName, this.event);
    }

    /**
     * Returns a string of the task (eg.: [E][ ] project meeting (at: Aug 6th 2-4pm) ).
     *
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + event + ")";
    }
}
