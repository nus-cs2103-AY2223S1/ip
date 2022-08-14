public class Deadline extends Task {

    private String endDate;

    public Deadline(String description, String endDate) {
        super(description);
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.endDate + ")";
    }
}