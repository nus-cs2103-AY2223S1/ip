public class Deadline extends Task {
    protected String dateAndTime;

    public Deadline(String deadline, String dateAndTime) {
        super(deadline);
        this.dateAndTime = dateAndTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateAndTime + ")";
    }
}
