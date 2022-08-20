public class Deadline extends Task {
    private String datetime;

    public Deadline(String description, String datetime) {
        super(description);
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + datetime + ")";
    }
}