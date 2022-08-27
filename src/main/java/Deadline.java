public class Deadline extends Task {

    protected String dateTime;

    public Deadline(String description, String dl) {
        super(description);
        this.dateTime = dl;
    }

    public String getDatetime() { return this.dateTime; }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTime + ")";
    }
}
