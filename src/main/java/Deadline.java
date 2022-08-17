public class Deadline extends Task {

    private String time;

    public Deadline(String taskname, String time) {
        super(taskname);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.time + ")";
    }
}
