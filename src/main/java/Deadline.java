public class Deadline extends Task {
    protected String type = "[D]";
    protected String dateTime = "(by: ";

    Deadline(String description, String dateTime) {
        super(description);
        this.dateTime = this.dateTime + dateTime + ")";
    }

    @Override
    public String toString() {
        return type + super.toString() + dateTime;
    }
}
