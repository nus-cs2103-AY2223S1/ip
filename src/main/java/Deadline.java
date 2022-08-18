public class Deadline extends Task {
    protected String dateTime;

    public Deadline(String dateTime, String description) {
        super(description);
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return " (by: " + this.dateTime + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + this.getDateTime();
    }
}