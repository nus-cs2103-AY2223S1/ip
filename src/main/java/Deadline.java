public class Deadline extends Task {
    private String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[" + TaskType.D + "]" + super.toString() + " (by: " + this.date + ")";
    }
}
