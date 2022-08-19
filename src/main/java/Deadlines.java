public class Deadlines extends Task{

    private String taskName;
    private String deadline;
    private boolean isDone = false;

    public Deadlines(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + "(by:" + deadline + ")";
    }
}
