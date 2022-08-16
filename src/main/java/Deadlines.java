public class Deadlines extends Task {

    private final String date;
    public Deadlines(String deadline, String date) {
        super(deadline);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + date + ")";
    }
}
