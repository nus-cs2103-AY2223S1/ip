public class Deadline extends Task {
    protected String time;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), time);
    }

    @Override
    public String getSaveFormat() {
        return String.format("D | %s | %s", super.getSaveFormat(), time);
    }
}
