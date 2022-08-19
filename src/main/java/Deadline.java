public class Deadline extends Task{
    private String dueDate;

    public Deadline(String taskName, String dueDate) {
        super(taskName);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + dueDate + ")";
    }
}
