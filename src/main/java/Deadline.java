public class Deadline extends Task {
    protected String description;
    protected boolean isDone;
    protected String deadlineDay;

    public Deadline (String description, String deadlineDay) {
        super(description);
        this.deadlineDay = deadlineDay.replaceFirst(" ", ": ");
    }

    @Override
    public String toString() {
        String s = String.format("[D]%s (%s)", super.toString(), this.deadlineDay);
        return s;
    }
}
