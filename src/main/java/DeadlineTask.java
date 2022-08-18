public class DeadlineTask extends Task {
    private final String deadline;
    DeadlineTask(String taskname, String deadline) {
        super(taskname);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
