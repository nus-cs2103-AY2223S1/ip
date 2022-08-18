public class DeadlineTask extends Task {

    String deadline;

    protected DeadlineTask(String taskTitle, String deadline) {
        super(taskTitle, TaskType.DEADLINE);
        this.deadline = deadline;
    }
}
