public class Event extends Task {
    /**
     * A public constructor to initialise an Event task.
     * @param description The details of the task.
     * @param time The time which the task must be done by.
     */
    public Event(String description, boolean isDone, String time) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + this.time + ")";
    }
}