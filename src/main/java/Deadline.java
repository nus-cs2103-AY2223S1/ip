public class Deadline extends Task {
    private String date;

    public Deadline(String msg, String date) {
        super(msg);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("%s%s (by: %s)", "[D]", super.toString(), this.date);
    }
}
