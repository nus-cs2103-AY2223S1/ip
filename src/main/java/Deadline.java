public class Deadline extends Task {
    private final String time;
    private static final String type = "[D]";

    public Deadline(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}
