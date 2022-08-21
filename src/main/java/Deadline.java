public class Deadline extends Task {

    private String time;
    private final char type = 'D';

    public Deadline(String taskname, String time) {
        super(taskname);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s (by: %s)", this.type, super.toString(), this.time);
    }

    @Override
    public String toSavedString() {
        return "" + this.type + "#" + super.toSavedString() + "#" + this.time;
    }
}
