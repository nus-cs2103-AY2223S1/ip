public class Deadline extends ScheduleTask {

    public Deadline(String description, String by) {
        super(description, by);
    }

    public Deadline(String description, String by, boolean done) {
        super(description, done);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + showDateTime() + ")";
    }
}
