public class Deadline extends Task {

    private String date;

    public Deadline(String description, String date) {
        super(description, "D");
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.date);
    }
}
