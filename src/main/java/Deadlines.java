public class Deadlines extends Task {
    private String dateTime;

    public Deadlines(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String savedTaskString() {
        return String.format("D|%d|%s|%s", this.isDone ? 1 : 0, this.description, this.dateTime);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTime + ")";
    }
}
