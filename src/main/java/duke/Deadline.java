package duke;

/**
 * Encapsulates a task with a deadline.
 */
public class Deadline extends TimedTask {

    public Deadline(String description, String time) {
        super(description, time);
    }

    public Deadline(String description, String time, boolean done) {
        super(description, time, done);
    }

    @Override
    public String toSaveData() {
        return "D|" + (super.isDone ? "1|" : "0|") + this.description + "|" + this.time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}
