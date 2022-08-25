public class Deadline extends Task {
    protected formatDate date;

    public Deadline(String description, String by) {
        super(description);
        this.date = new formatDate(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.date + ")";
    }
}
