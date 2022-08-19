public class Deadline extends Task {
    protected String time;

    public Deadline(String description, String time) {
        super(description, Tag.D);
        this.time = time;
    }

    @Override
    public String getDescription() {
        return description + " (" + time + ")";
    }
}
