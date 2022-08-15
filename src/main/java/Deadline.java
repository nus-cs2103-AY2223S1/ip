public class Deadline extends Task {
    /**
     * A public constructor to initialise a Deadline task.
     * @param description The details of the task.
     * @param time The time which the task must be done by.
     */
    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.time + ")";
    }
}