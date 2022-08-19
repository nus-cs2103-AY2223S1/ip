public class EventTask extends Task {

    String dateTime;

    protected EventTask(String taskTitle, String dateTime) {
        super(taskTitle, TaskType.EVENT);
        this.dateTime = dateTime;
    }
}
