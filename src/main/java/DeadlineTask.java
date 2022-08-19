public class DeadlineTask extends Task {
    private final String date;
    DeadlineTask(String task, String date) {
        super(task);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + date + ")";
    }
}