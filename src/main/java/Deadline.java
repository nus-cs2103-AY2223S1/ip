public class Deadline extends Task {

    private String date;

    public Deadline(String description, String date, boolean isDone) {
        super(description, "D", isDone);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.date);
    }

    @Override
    public String getCsvString() {
        return String.format("deadline %s /by %s", super.getCsvString(), date);
    }
}
