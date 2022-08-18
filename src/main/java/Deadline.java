public class Deadline extends Task {

    private String time;

    public Deadline(String command, String time) {
        super(command);
        this.time = time;
    }

    @Override
    public String toString() {
        if (done) {
            return "[D][X] " + this.description + " (by: " + this.time + ")";
        } else {
            return "[D][ ] " + this.description + " (by: " + this.time + ")";
        }
    }
}
