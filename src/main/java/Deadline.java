public class Deadline extends Task {

    protected String dl;

    public Deadline(String description, String dl) {
        super(description);
        this.dl = dl;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + dl + ")";
    }
}
