public class Deadline extends Task {
    private static final String typeIcon = "D";
    private String time;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", typeIcon, super.toString(), this.time);
    }
}
