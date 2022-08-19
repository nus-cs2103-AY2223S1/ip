public class Deadline extends Task{
    protected String deadline;
    public Deadline(String description, String deadline, String taskType) {
        super(description, taskType);
        this.deadline = deadline;
    }
    public String getDeadline() {
        return deadline;
    }
}
