public class Deadline extends ScheduleTask {

    public Deadline(String description, String by) {
        super(description, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + showDateTime() + ")";
    }
}
